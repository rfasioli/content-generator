package br.com.rfasioli.ContentGenerator.service.processor.directive;

import org.json.JSONObject;

/**
 * @author rodrigo fasioli
 */
public interface DirectiveCommander {
	public String execute(String input, JSONObject source, String tag);
}
