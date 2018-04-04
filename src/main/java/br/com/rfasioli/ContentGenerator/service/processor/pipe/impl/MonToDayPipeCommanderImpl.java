package br.com.rfasioli.ContentGenerator.service.processor.pipe.impl;

import br.com.rfasioli.ContentGenerator.service.processor.pipe.PipeCommander;

public class MonToDayPipeCommanderImpl implements PipeCommander {
	@Override
	public String execute(String input) {
		String result = "";
		try {
			int days = Integer.parseInt(input) * 30;
			result = Integer.toString(days, 10);
		} catch (NumberFormatException ex) {
			result = "NaN";
		}
		return result;
	}
}
