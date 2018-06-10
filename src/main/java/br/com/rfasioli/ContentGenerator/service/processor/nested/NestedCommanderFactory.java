package br.com.rfasioli.ContentGenerator.service.processor.nested;

import br.com.rfasioli.ContentGenerator.service.processor.nested.impl.AddAfterNestedCommanderImpl;
import br.com.rfasioli.ContentGenerator.service.processor.nested.impl.AppendNestedCommanderImpl;
import br.com.rfasioli.ContentGenerator.service.processor.nested.impl.ReplaceNestedCommanderImpl;

/**
 * @author rodrigo fasioli
 */
public class NestedCommanderFactory {
	public static NestedCommander getNestedCommander(String action) {
		NestedCommander obj = null;
		
		//TODO - usar reflection
//		Reflections reflections = new Reflections("br.com.rfasioli.ContentGenerator.service.processor.nested");    
//		Set<Class<? extends MyInterface>> classes = reflections.getSubTypesOf(MyInterface.class);
		
		if (action == null || action.isEmpty() || action.toUpperCase().equals("APPEND")) {
			obj = new AppendNestedCommanderImpl();
		} else if (action.toUpperCase().equals("ADD")) {
			obj = new AddAfterNestedCommanderImpl();
		}
		else if (action.toUpperCase().equals("REPLACE")) {
			obj = new ReplaceNestedCommanderImpl();
		}

		return obj;
	}

}
