package br.com.rfasioli.ContentGenerator.service.processor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe utilitária para processamento do conteúdo gerado.
 * @author rodrigo fasioli
 *
 */
public class ProcessorUtilities {

	private static final Logger logger = LoggerFactory.getLogger(ProcessorUtilities.class);

	/**
	 * Substitui os valores entre os delimitadores <%= %>, que indicam um Path json, pelo valor da variável correspondente do objeto json. 
	 * @param source Texto onde devem ser feitas as substituições.
	 * @param obj Objeto json que deve conter os valores para substituição
	 * @return Texto com as substituições efetuadas
	 */
	public static String replaceTags(String source, JSONObject obj) {
	    Pattern pattern = Pattern.compile("<%=(.*?)%>");
	    Matcher matcher = pattern.matcher(source);
	    while (matcher.find()) {
	        source = source.replaceAll(matcher.group(), getValueFromJsonString(obj, matcher.group(1)));
		    logger.debug("Processando: " + source);
	    }
	    return source;
	}

	/**
	 * Extrai um valor do objeto json para o caminho informado.
	 * @param json Objeto json.
	 * @param field Path para o valor a ser extraido.
	 * @return O valor obtido para o patj informado
	 */
	public static String getValueFromJsonString(JSONObject json, String field) {
		String value = "";
		if (field == null) 
			return value;
		
		String[] fields = field.trim().split("\\.");
		
		for (int i = 0; i < fields.length; i++) {
			if (json == null) 
				break;
			
			try {
				json =  json.getJSONObject(fields[i].trim());
			} catch (JSONException ex) {
				logger.debug("Erro extraindo o node como um objecto, tentará extrair como valor.");
				try {
					value = json.get(fields[i]).toString();
				} catch (JSONException e) {
					logger.warn(String.format("Erro extraindo o node como um valor. field[%s] json[%s]", field, json.toString()));
					break;
				}        	
			}
		}
		
	    return value;
	}

	/**
	 * Extrai um Array do objeto json para o caminho informado.
	 * @param json Objeto json.
	 * @param field Path para o valor a ser extraido.
	 * @return O valor obtido para o patj informado
	 */
	public static JSONArray getJsonArrayObj(JSONObject json, String field) {
		JSONArray ret = null;

		if (field == null) 
			return ret;
		
		try {
			String[] fields = field.trim().split("\\.");
			if (fields.length > 1) {
				for (int i = 1; i < fields.length; i++)
					field = fields[i];
				ret = getJsonArrayObj(json.getJSONObject(fields[0]), field);
			}
			else
				ret = json.getJSONArray(fields[0]);
		}
		catch (JSONException ex) {
			logger.warn(String.format("Erro obtendo node como um array. field[%s] json[%s]", field, json.toString()));
		}
		return ret;
	}

	public ProcessorUtilities() {
		super();
	}

}
