package br.com.rfasioli.ContentGenerator.service.processor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessorUtilities {

	private static final Logger logger = LoggerFactory.getLogger(ProcessorUtilities.class);

	public static String replaceTags(String str, JSONObject obj) {
	    Pattern pattern = Pattern.compile("<%=(.*?)%>");
	    Matcher matcher = pattern.matcher(str);
	    while (matcher.find()) {
	        str = str.replaceAll(matcher.group(), getValueFromJsonString(obj, matcher.group(1)));
		    System.out.println("Processando: " + str);
	    }
	    return str;
	}

	public static String getValueFromJsonString(JSONObject json, String field) {
		String value = "";
		try {
			String[] fields = field.trim().split("\\.");
			for (int i = 0; i < fields.length; i++) {
				if (json == null) { break; }
				try {
					json =  json.getJSONObject(fields[i].trim());
				} catch (JSONException ex) {
					logger.debug("ProcessorUtilities getValueFromJsonString", ex);
					value = json.get(fields[i]).toString();
				}
			}
		} catch (JSONException ex) {
			logger.warn("ProcessorUtilities getValueFromJsonString", ex);
		}        	
	    return value;
	}

	public static JSONArray getJsonArrayObj(JSONObject json, String field) {
		JSONArray ret = null;
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
			logger.warn("ProcessorUtilities getJsonArrayObj", ex);
		}
		return ret;
	}

	public ProcessorUtilities() {
		super();
	}

}
