package br.com.rfasioli.ContentGenerator.service.processor.directive;

import br.com.rfasioli.ContentGenerator.service.processor.directive.impl.IfDirectiveCommanderImpl;
import br.com.rfasioli.ContentGenerator.service.processor.directive.impl.LoopDirectiveCommanderImpl;

/**
 * @author rodrigo fasioli
 */
public class DirectiveCommanderFactory {
	public static DirectiveCommander getDirectiveCommander(String directive) {
		DirectiveCommander obj = null;
		
		//TODO - usar reflection
//		Reflections reflections = new Reflections("br.com.rfasioli.ContentGenerator.service.processor.directive");    
//		Set<Class<? extends MyInterface>> classes = reflections.getSubTypesOf(MyInterface.class);
		
		if (directive.toUpperCase().equals("IF")) {
			obj = new IfDirectiveCommanderImpl();
		}
		else if (directive.toUpperCase().equals("LOOP")) {
			obj = new LoopDirectiveCommanderImpl();
		}

		return obj;
	}
}
