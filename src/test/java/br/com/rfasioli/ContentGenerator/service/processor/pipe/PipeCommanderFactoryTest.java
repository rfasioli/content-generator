package br.com.rfasioli.ContentGenerator.service.processor.pipe;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.rfasioli.ContentGenerator.service.processor.pipe.impl.DatePipeCommanderImpl;
import br.com.rfasioli.ContentGenerator.service.processor.pipe.impl.MonToDayPipeCommanderImpl;

public class PipeCommanderFactoryTest {

	@Test
	public void testGetPipeCommander_DatePipeCommanderImpl() {
		PipeCommander pipeCommander = PipeCommanderFactory.getPipeCommander("DATE");
		assertTrue(pipeCommander instanceof DatePipeCommanderImpl);
	}

	@Test
	public void testGetPipeCommander_MonToDayPipeCommanderImpl() {
		PipeCommander pipeCommander = PipeCommanderFactory.getPipeCommander("MON2DAY");
		assertTrue(pipeCommander instanceof MonToDayPipeCommanderImpl);
	}

	@Test
	public void testGetPipeCommander_InvalidTag() {
		PipeCommander pipeCommander = PipeCommanderFactory.getPipeCommander("");
		assertTrue(pipeCommander == null);
	}

}
