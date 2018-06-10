package br.com.rfasioli.ContentGenerator.service.processor;

import static org.junit.Assert.assertNotNull;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class DocumentProcessorTest {
	
	private DocumentProcessor documentProcessor;

	@Before
	public void setUp() throws Exception {
		JSONObject json = null;
		String document = null;
		this.documentProcessor = new DocumentProcessor(json, document);
	}

	@Test
	public void testProcess() {
		String result = this.documentProcessor.process();
		assertNotNull(result);
	}

}
