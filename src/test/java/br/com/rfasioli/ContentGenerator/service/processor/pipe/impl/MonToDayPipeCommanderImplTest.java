package br.com.rfasioli.ContentGenerator.service.processor.pipe.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.rfasioli.ContentGenerator.service.processor.pipe.PipeCommander;

public class MonToDayPipeCommanderImplTest {

	@Test
	public void testExecute() {
		PipeCommander pipe = new MonToDayPipeCommanderImpl();
		String result = pipe.execute("6");
		assertTrue(result.equals("180"));
	}
	
	@Test
	public void testExecute_NaN() {
		PipeCommander pipe = new MonToDayPipeCommanderImpl();
		String result = pipe.execute("X");
		assertTrue(result.equals("NaN"));
	}

}
