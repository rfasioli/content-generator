package br.com.rfasioli.ContentGenerator.service;

import java.util.Arrays;
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
import br.com.rfasioli.ContentGenerator.document.Query;
import br.com.rfasioli.ContentGenerator.document.Rule;
import br.com.rfasioli.ContentGenerator.document.Template;
import br.com.rfasioli.ContentGenerator.document.TemplateBase;
import br.com.rfasioli.ContentGenerator.document.TemplateNested;
import br.com.rfasioli.ContentGenerator.repository.FragmentRepository;
import br.com.rfasioli.ContentGenerator.repository.TemplateRepository;
import br.com.rfasioli.ContentGenerator.service.processor.DocumentProcessor;
import br.com.rfasioli.ContentGenerator.service.processor.nested.NestedCommander;
import br.com.rfasioli.ContentGenerator.service.processor.nested.NestedCommanderFactory;
import br.com.rfasioli.ContentGenerator.util.pdfGenerator;

@Service
public class ReportGeneratorService {

	private static final Logger logger = LoggerFactory.getLogger(ReportGeneratorService.class);

	@Autowired 
	private TemplateRepository templateRepo;
	
	@Autowired
	private FragmentRepository fragmentRepo;
	
	/**
	 * Gera relatório
	 * @param request dados da requisição para gerar o relatório
	 * @return
	 */
	public String generateReport(String request) {
		Map<String, String> docTemplate = new LinkedHashMap<>();
		Iterable<Template> templates = null;

		String result = null;
		
		try {
			templates = templateRepo.findByRulesQueriesStatementFieldAndRulesQueriesStatementOperatorAndRulesQueriesStatementValues
							("reportId", "eq", getValueFromJsonString(request, "reportId"));

			for (Template template : templates) {
				processTemplate(template, request, docTemplate);
			}
		
			DocumentProcessor documentProcessor = new DocumentProcessor(new JSONObject(request), this.buildStringFromMap(docTemplate));
			result = documentProcessor.process();
		}
		catch (Exception ex) {
			logger.error("generateReport ", ex);
		}
		
		return result;
	}
	
	public byte[] generatePdfReport(String request) {
		byte[] result = null;
		String xHtmlDocument = "";
		
		try {
			xHtmlDocument = 
					"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" + 
					"<html xmlns=\"http://www.w3.org/1999/xhtml\">" +
						"<body>" +
							generateReport(request) +
						"</body>" +
					"</html>";
			result = pdfGenerator.fromXHtmlContentToBase64(xHtmlDocument);
		} catch (Throwable ex) {
			logger.error("generatePdfReport ", ex);
			result = null;
		}
		
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
			Map<String, String> docTemplate) {
		for (Rule rule : template.getRules()) {
			if (processRule(rule, request)) {
				if (template instanceof TemplateNested) {
					TemplateNested nested = (TemplateNested) template;
					applyFragments(template.getFragments(), docTemplate, nested.getAction(), nested.getReference());
				}
				else {
					applyFragments(template.getFragments(), docTemplate, null, null);
				}
			}
		}
		
		List<?> nested = null;
		if (template instanceof Template) {
			nested = ((Template)template).getNested();
		} else if (template instanceof TemplateNested) {
			nested = ((TemplateNested)template).getNested();
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
			Rule rule, 
			String request) {
		boolean result = false;
		for (Query query : rule.getQueries()) {
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
	
	private boolean processQuery(Query query, String request) {
		String field = query.getStatement().getField();
		String operator = query.getStatement().getOperator();
		List<String> values = Arrays.asList(query.getStatement().getValues());
		
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
	 * @param fragmentsTags
	 * @param docTemplate
	 * @param action
	 * @param reference
	 */
	private void applyFragments(String[] fragmentsTags, Map<String, String> docTemplate, String action, String reference) {
		for (String tag : fragmentsTags) {
			logger.debug("To be, apply " + action + " fragment " + tag + " on " + reference);
			Fragment fragment = fragmentRepo.findOne(tag);
			NestedCommander commander = NestedCommanderFactory.getNestedCommander(action);
			if (commander != null) {
				commander.execute(fragment, docTemplate, reference);
			}
		}
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
		
		System.out.println(stringBuilder);
		
		return stringBuilder;
	}
	
	public ReportGeneratorService() {
		super();
	}


}
