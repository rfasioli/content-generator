package br.com.rfasioli.ContentGenerator.service.processor.directive.impl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rfasioli.ContentGenerator.service.processor.ProcessorUtilities;
import br.com.rfasioli.ContentGenerator.service.processor.directive.DirectiveCommander;

/**
 * @author rodrigo fasioli
 */
public class LoopDirectiveCommanderImpl implements DirectiveCommander {

	private static final Logger logger = LoggerFactory.getLogger(LoopDirectiveCommanderImpl.class);

	@Override
	public String execute(String input, JSONObject source, String tag) {
		StringBuilder out = new StringBuilder();
		try {
			JSONArray arr = ProcessorUtilities.getJsonArrayObj(source, tag);
			for (int i = 0; i < arr.length(); i++) {
				JSONObject obj = arr.getJSONObject(i);
				out.append(ProcessorUtilities.replaceTags(input, obj));
			}
		} catch (JSONException ex) {
			logger.warn("LoopDirectiveCommanderImpl execute", ex);
		}
		logger.debug("Loop Processado: {}", out.toString());
		return out.toString();
	}
	
}

