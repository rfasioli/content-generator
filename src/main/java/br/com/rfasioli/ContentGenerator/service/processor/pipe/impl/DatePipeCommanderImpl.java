package br.com.rfasioli.ContentGenerator.service.processor.pipe.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.rfasioli.ContentGenerator.service.processor.pipe.PipeCommander;

public class DatePipeCommanderImpl implements PipeCommander {

	@Override
	public String execute(String input) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date(Long.valueOf(input));
		return df.format(date);
	}
}
