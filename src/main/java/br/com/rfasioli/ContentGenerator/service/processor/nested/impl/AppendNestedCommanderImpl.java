package br.com.rfasioli.ContentGenerator.service.processor.nested.impl;

import java.util.Map;

import br.com.rfasioli.ContentGenerator.document.Fragment;
import br.com.rfasioli.ContentGenerator.service.processor.nested.NestedCommander;

/**
 * @author rodrigo fasioli
 */
public class AppendNestedCommanderImpl implements NestedCommander {

	@Override
	public void execute(Fragment fragment, Map<String, String> docTemplate, String reference) {
		docTemplate.put(fragment.getId(), fragment.getContent());
	}

}
