package br.com.rfasioli.ContentGenerator.service.processor.nested.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import br.com.rfasioli.ContentGenerator.document.Fragment;
import br.com.rfasioli.ContentGenerator.service.processor.nested.NestedCommander;

/**
 * @author rodrigo fasioli
 */
public class ReplaceNestedCommanderImpl implements NestedCommander {

	@Override
	public void execute(Fragment fragment, Map<String, String> docTemplate, String reference) {
		Map<String, String> aux = new LinkedHashMap<>(docTemplate);
		docTemplate.clear();		
		for (Map.Entry<String, String> entry : aux.entrySet()) {
			if (entry.getKey().equals(reference)) {
				docTemplate.put(fragment.getId(), fragment.getContent());
			}
			else {
				docTemplate.put(entry.getKey(), entry.getValue());
			}
		}
	}

}
