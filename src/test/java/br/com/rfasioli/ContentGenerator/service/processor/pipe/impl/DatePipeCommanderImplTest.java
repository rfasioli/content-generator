package br.com.rfasioli.ContentGenerator.service.processor.pipe.impl;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import br.com.rfasioli.ContentGenerator.service.processor.pipe.PipeCommander;

public class DatePipeCommanderImplTest {

	@Test
	public void testExecute() {
		Date date = new Date();
		PipeCommander pipe = new DatePipeCommanderImpl();
		String result = pipe.execute(String.valueOf(date.getTime()));
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = df.format(date);
		
		assertTrue(result.equals(strDate));
	}

}
