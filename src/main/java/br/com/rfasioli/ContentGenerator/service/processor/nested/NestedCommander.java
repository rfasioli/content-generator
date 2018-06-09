package br.com.rfasioli.ContentGenerator.service.processor.nested;

import java.util.Map;

import br.com.rfasioli.ContentGenerator.document.Fragment;

/**
 * @author rodrigo fasioli
 */
public interface NestedCommander {
	public void execute(
			Fragment fragment, 
			Map<String, String> docTemplate, 
			String reference);
}
