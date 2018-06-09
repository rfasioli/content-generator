package br.com.rfasioli.ContentGenerator.service.processor;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import br.com.rfasioli.ContentGenerator.service.processor.directive.DirectiveCommander;
import br.com.rfasioli.ContentGenerator.service.processor.directive.DirectiveCommanderFactory;
import br.com.rfasioli.ContentGenerator.service.processor.pipe.PipeCommander;
import br.com.rfasioli.ContentGenerator.service.processor.pipe.PipeCommanderFactory;

/**
 * @author Rodrigo Fasioli
 *
 */
public class DocumentProcessor {
	
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
		StringBuilder output = new StringBuilder(this.document);
		System.out.println("Inicio: " + this.document);
		processDirectives(output);
		System.out.println("Output Diretivas: " + output.toString());
		processReplacements(output);
		System.out.println("Output Replace: " + output.toString());
		return output.toString();
	}

	/**
	 * @param output
	 */
	private void processDirectives(StringBuilder output) {
		List<DocumentFragment> fragments = new ArrayList<DocumentFragment>();
	    Pattern pattern = Pattern.compile("<%=(.*?)%>");
	    Matcher matcher = pattern.matcher(this.document);
	    
	    while (matcher.find()) {
	    	int start, end, startBuffer, endBuffer;
	    	String[] values = matcher.group(1).split(":");
	    	if (values.length < 2 || values[0].equals("end"))
	    		continue;
	    	
	    	String endTag = "<%=end:" + values[0] + "%>";
    		start = matcher.start();
    		end = output.indexOf(endTag);
    		
    		if (end < 0) //TODO - error handling...
    			continue;
    		
    		startBuffer = start + matcher.group().length();
    		endBuffer = end;
    		end += endTag.length();    		
    		//TODO - validate sub tags, not supported at this time
    		
	    	DirectiveCommander obj = DirectiveCommanderFactory.getDirectiveCommander(values[0]);
	    	
	    	String result = obj.execute(this.document.substring(startBuffer, endBuffer), this.source, values[1]);
	    	System.out.println("Diretiva processada: " + result);
	    	fragments.add(new DocumentFragment(start, end, result));
	    }

    	System.out.println("Total de Diretivas: " + fragments.size());

	    for (int i = fragments.size(); i > 0; i--) {
	    	System.out.println("Atualizando: " + output);
	    	output.replace(
	    			fragments.get(i - 1).getStart(), 
	    			fragments.get(i - 1).getEnd(),
	    			fragments.get(i - 1).getFragment());
	    }
    	System.out.println("Atualizado: " + output);
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
		    System.out.println("Processando: " + output.toString());
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
	    System.out.println("Processando: " + output.toString());
	}
	
}
