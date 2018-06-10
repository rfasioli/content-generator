package br.com.rfasioli.ContentGenerator.service.processor;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rfasioli.ContentGenerator.service.processor.directive.DirectiveCommander;
import br.com.rfasioli.ContentGenerator.service.processor.directive.DirectiveCommanderFactory;
import br.com.rfasioli.ContentGenerator.service.processor.pipe.PipeCommander;
import br.com.rfasioli.ContentGenerator.service.processor.pipe.PipeCommanderFactory;

/**
 * @author Rodrigo Fasioli
 *
 */
public class DocumentProcessor {

	private static final Logger logger = LoggerFactory.getLogger(DocumentProcessor.class);
	
	private JSONObject source;
	private String document;
	

	public DocumentProcessor() {
		super();
	}
	
	public DocumentProcessor(JSONObject source, String document) {
		this.source = source;
		this.document = document;
	}
	
	
	/**
	 * @return
	 */
	public String process() {
		if (this.document == null)
			return new String();
		
		StringBuilder output = new StringBuilder(this.document);
		if (this.source != null) {
			logger.debug("Inicio: " + this.document);
			processDirectives(output);
			logger.debug("Output Diretivas: " + output.toString());
			processReplacements(output);
			logger.debug("Output Replace: " + output.toString());
		}
		
		return output.toString();
	}

	/**
	 * @param output
	 */
	private void processDirectives(StringBuilder output) {
		List<DocumentFragment> fragments = new ArrayList<>();
	    Pattern pattern = Pattern.compile("<%=(.*?)%>");
	    Matcher matcher = pattern.matcher(this.document);
	    
	    while (matcher.find()) {
	    	proccessDiretive(output, matcher, fragments);
	    }
	    logger.debug("Total de Diretivas: " + fragments.size());
	    
	    proccessOutput(output, fragments);
	    
	    logger.debug("Atualizado: " + output);
	}

	/**
	 * @param output
	 * @param matcher
	 * @param fragments
	 */
	private void proccessDiretive(StringBuilder output, Matcher matcher, List<DocumentFragment> fragments) {
    	int start, end, startBuffer, endBuffer;
    	String[] values = matcher.group(1).split(":");
    	if (values.length < 2 || values[0].equals("end"))
    		return;
    	
    	String endTag = "<%=end:" + values[0] + "%>";
		start = matcher.start();
		end = output.indexOf(endTag);
		
		if (end < 0) //TODO - error handling...
			return;
		
		startBuffer = start + matcher.group().length();
		endBuffer = end;
		end += endTag.length();    		
		//TODO - validate sub tags, not supported at this time
		
    	DirectiveCommander obj = DirectiveCommanderFactory.getDirectiveCommander(values[0]);
    	
    	String result = obj.execute(this.document.substring(startBuffer, endBuffer), this.source, values[1]);
    	logger.debug("Diretiva processada: " + result);
    	fragments.add(new DocumentFragment(start, end, result));		
	}
	
    /**
     * @param output
     * @param fragments
     */
    private void proccessOutput(StringBuilder output, List<DocumentFragment> fragments) {
	    for (int i = fragments.size(); i > 0; i--) {
	    	logger.debug("Atualizando: " + output);
	    	output.replace(
	    			fragments.get(i - 1).getStart(), 
	    			fragments.get(i - 1).getEnd(),
	    			fragments.get(i - 1).getFragment());
	    }
    }
	
	/**
	 * @param output
	 */
	private void processReplacements(StringBuilder output) {
	    //String str = output.toString();
	    Pattern pattern = Pattern.compile("<%=(.*?)%>");
	    Matcher matcher = pattern.matcher(output.toString());

	    while (matcher.find()) {
	    	if (matcher.group(1).indexOf(':') > 0) {
	    		continue;
	    	}
	    	logger.debug("Processando: " + output.toString());
	    	String[] tags = matcher.group(1).split("\\|");
	    	String value = ProcessorUtilities.getValueFromJsonString(source, tags[0]);
	    	if (tags.length >= 2) {
	    		PipeCommander pipe = PipeCommanderFactory.getPipeCommander(tags[1]);
			    value = pipe.execute(value);
	    	}
		    
		    output.replace(
		    			output.indexOf(matcher.group()),
		    			output.indexOf(matcher.group()) + matcher.group().length(),
		    			value);
		    
	    } 
	    logger.debug("Processando: " + output.toString());
	}
	
}
