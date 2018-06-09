package br.com.rfasioli.ContentGenerator.service.processor.directive;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.rfasioli.ContentGenerator.service.processor.directive.impl.IfDirectiveCommanderImpl;
import br.com.rfasioli.ContentGenerator.service.processor.directive.impl.LoopDirectiveCommanderImpl;

public class DirectiveCommanderFactoryTest {

	@Test
	public void test_if() {
		DirectiveCommander directiveCommander = DirectiveCommanderFactory.getDirectiveCommander("IF");
		assertTrue(directiveCommander instanceof IfDirectiveCommanderImpl);
	}
	
	@Test
	public void test_loop() {
		DirectiveCommander directiveCommander = DirectiveCommanderFactory.getDirectiveCommander("LOOP");
		assertTrue(directiveCommander instanceof LoopDirectiveCommanderImpl);
	}

}
