package br.com.rfasioli.ContentGenerator.service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rfasioli.ContentGenerator.document.Fragment;
import br.com.rfasioli.ContentGenerator.document.Template;
import br.com.rfasioli.ContentGenerator.document.TemplateBase;
import br.com.rfasioli.ContentGenerator.dto.QueryDto;
import br.com.rfasioli.ContentGenerator.dto.RuleDto;
import br.com.rfasioli.ContentGenerator.dto.TemplateNestedDto;
import br.com.rfasioli.ContentGenerator.exception.MissingParameterException;
import br.com.rfasioli.ContentGenerator.exception.PdfGenerationException;
import br.com.rfasioli.ContentGenerator.repository.TemplateRepository;
import br.com.rfasioli.ContentGenerator.service.processor.DocumentProcessor;
import br.com.rfasioli.ContentGenerator.service.processor.nested.NestedCommander;
import br.com.rfasioli.ContentGenerator.service.processor.nested.NestedCommanderFactory;
import br.com.rfasioli.ContentGenerator.util.pdfGenerator;

/**
 * @author rodrigo fasioli
 *
 */
@Service
public class ContentGeneratorService {

	private static final Logger logger = LoggerFactory.getLogger(ContentGeneratorService.class);

	@Autowired 
	private TemplateRepository templateRepo;
	
	/**
	 * Gera conteúdo conforme a requisição associada à parametrização. 
	 * @param source dados da requisição para gerar o relatório
	 * @return Conteúdo obtido.
	 * @throws MissingParameterException 
	 */
	public String generateContent(String source) throws MissingParameterException {
		Map<String, String> docTemplate = new LinkedHashMap<>();
		Iterable<Template> templates = null;

		String result = null;
		
		templates = templateRepo.findByRulesQueriesStatementFieldAndRulesQueriesStatementOperatorAndRulesQueriesStatementValues
						("reportId", "eq", getValueFromJsonString(source, "reportId"));

		for (Template template : templates) {
			processTemplate(template, source, docTemplate);
		}
	
		try {
			DocumentProcessor documentProcessor = new DocumentProcessor(
					new JSONObject(source), this.buildStringFromMap(docTemplate));
			result = documentProcessor.process();
		} catch (JSONException ex) {
			throw new MissingParameterException("Source parameter not a JSON String.", ex);
		}
		
		return result;
	}
	
	/**
	 * Gera conteúdo conforme a requisição associada à parametrização. 
	 * @param source dados da requisição para gerar o relatório
	 * @return Arquivo pdf em base64 com o conteúdo obtido.
	 * @throws MissingParameterException
	 * @throws IOException
	 * @throws PdfGenerationException
	 */
	public byte[] generatePdfReport(String source) throws MissingParameterException, IOException, PdfGenerationException {
		byte[] result = null;
		String xHtmlDocument = "";
		
		xHtmlDocument = 
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">" +
					"<body>" +
						generateContent(source) +
					"</body>" +
				"</html>";
		result = pdfGenerator.fromXHtmlContentToBase64(xHtmlDocument);
		
		return result;
	}
	
	/**
	 * Processa template carregado
	 * @param template
	 * @param request
	 * @param docTemplate
	 */
	private void processTemplate(
			TemplateBase template, 
			String request,
			Map<String, String> docTemplate) 
	{
		for (RuleDto rule : template.getRules()) {
			if (processRule(rule, request)) {
				if (template instanceof TemplateNestedDto) {
					TemplateNestedDto nested = (TemplateNestedDto) template;
					template.getFragments().stream().forEach(
							fragment -> applyFragment(
									fragment, docTemplate, nested.getAction(), nested.getReference()));
				}
				else {
					template.getFragments().stream().forEach(
							fragment -> applyFragment(
									fragment, docTemplate, null, null));
				}
			}
		}
		
		List<?> nested = null;
		if (template instanceof Template) {
			nested = ((Template)template).getNested();
		} else if (template instanceof TemplateNestedDto) {
			nested = ((TemplateNestedDto)template).getNested();
		}
		if (nested != null) {
			for (Object object : nested) {
				processTemplate((TemplateBase)object, request, docTemplate);
			}
		}
	}

	/**
	 * Valida regra para aplicação do template
	 * @param rule regra
	 * @param request dados da requisição
	 * @return Regra é valida ou não
	 */
	private boolean processRule(
			RuleDto rule, 
			String request) {
		boolean result = false;
		for (QueryDto query : rule.getQueries()) {
			boolean aux_result = processQuery(query, request);
			String operator = query.getOperator();
			result = processLogicalOperator(operator, result, aux_result);
		}		
		return result;
	}

	/**
	 * Processa operadores lógicos
	 * @param operator operador
	 * @param previousResult resultado anterior
	 * @param result resultado
	 * @return resultado da operação lógica
	 */
	private boolean processLogicalOperator(String operator, boolean previousResult, boolean result) {
		if (operator == null) {
			return result;
		}
		return (operator.toLowerCase().equals("and") ?
				previousResult && result : 
				previousResult || result);		
	}
	
	private boolean processQuery(QueryDto query, String request) {
		String field = query.getStatement().getField();
		String operator = query.getStatement().getOperator();
		List<String> values = query.getStatement().getValues();
		
		String currentValue = getValueFromJsonString(request, field);
		
		return processRelationalOperators(operator, values, currentValue);
	}

	/**
	 * Processa operadores de relacionamento
	 * @param operator Operador
	 * @param values Valor do objeto
	 * @param compareValue Valor de referência
	 * @return
	 */
	private boolean processRelationalOperators(String operator, List<String> values, String compareValue) {
		boolean result = false;
		int size;
		if (values == null || operator == null || compareValue == null) {
			return false;
		}
		size = values.size(); 
		if (operator.toLowerCase().equals("eq") || operator.toLowerCase().equals("neq")) {
			if (size <= 0) { return false; }
			result = values.contains(compareValue);
			result = (operator.equals("eq") ? result : !result);
		}
		else {
			if (values.size() > 1) { return false; }
			String firstValue = values.get(0);
			Integer compare = compareValue.compareTo(firstValue);
			if (operator.toLowerCase().equals("gt") ) {
				result = (compare > 0);
			}
			else if (operator.toLowerCase().equals("lt") ) {
				result = (compare < 0);			
			}
			else if (operator.toLowerCase().equals("ge") ) {
				result = (compare >= 0);				
			}
			else if (operator.toLowerCase().equals("le") ) {
				result = (compare <= 0);
			}
		}
		return result;
	}

	/**
	 * @param data
	 * @param field
	 * @return
	 */
	private String getValueFromJsonString(String data, String field) {
		JSONObject json;
		String value = "";
		try {
			json = new JSONObject(data);
			String[] fields = field.split("\\.");
			
			for (int i = 0; i < fields.length; i++) {
				try {
					json = json.getJSONObject(fields[i]);
				} catch (JSONException ex) {
					logger.debug("ReportGeneratorService getValueFromJsonString", ex);
					value = json.get(fields[i]).toString();
				}
			}
		} catch (JSONException e) {
			logger.error("ReportGeneratorService getValueFromJsonString", e);
		}        	
	    return value;
	}
		
	/**
	 * @param fragment
	 * @param docTemplate
	 * @param action
	 * @param reference
	 * @return
	 */
	private boolean applyFragment(Fragment fragment, Map<String, String> docTemplate, String action, String reference) {
		NestedCommander commander = NestedCommanderFactory.getNestedCommander(action);
		if (commander != null) {
			commander.execute(fragment, docTemplate, reference);
		}		
		return true;
	}

	/**
	 * @param docTemplate
	 * @return
	 */
	public String buildStringFromMap(Map<String, String> docTemplate) {
		String stringBuilder = "";
		
		for(Map.Entry<String, String> fragment : docTemplate.entrySet()) {
			stringBuilder += fragment.getValue();
		}
		
		logger.debug(stringBuilder);
		
		return stringBuilder;
	}
	
	public ContentGeneratorService() {
		super();
	}

}
