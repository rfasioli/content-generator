package br.com.rfasioli.ContentGenerator.service.processor.pipe;

import br.com.rfasioli.ContentGenerator.service.processor.pipe.impl.DatePipeCommanderImpl;
import br.com.rfasioli.ContentGenerator.service.processor.pipe.impl.MonToDayPipeCommanderImpl;

/**
 * @author guilherme maegava
 */
public class PipeCommanderFactory {
	public static PipeCommander getPipeCommander(String pipe) {
		PipeCommander obj = null;
		
		switch(pipe.toUpperCase()) {
		case "DATE":
			obj = new DatePipeCommanderImpl();
			break;
		case "MON2DAY":
			obj = new MonToDayPipeCommanderImpl();
		}

		return obj;
	}

}
