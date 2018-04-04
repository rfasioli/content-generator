package br.com.rfasioli.ContentGenerator.service.processor.directive;

import org.json.JSONObject;

public interface DirectiveCommander {
	public String execute(String input, JSONObject source, String tag);
}
