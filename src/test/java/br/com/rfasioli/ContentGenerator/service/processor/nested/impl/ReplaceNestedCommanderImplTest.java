package br.com.rfasioli.ContentGenerator.service.processor.nested.impl;

import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.rfasioli.ContentGenerator.document.Fragment;
import br.com.rfasioli.ContentGenerator.service.processor.nested.NestedCommander;

public class ReplaceNestedCommanderImplTest {

	private NestedCommander nestedCommander;
	private Fragment fragment;
	private Map<String, String> docTemplate;
	
	@Before
	public void setUp() {
		this.nestedCommander = new ReplaceNestedCommanderImpl();
		
		this.fragment = new Fragment("10", "fragmento 10", "conteudo do fragmento 10", null);
		
		this.docTemplate = new LinkedHashMap<>();
		this.docTemplate.put("1", "value");
		this.docTemplate.put("2", "value");
		this.docTemplate.put("3", "value");
		this.docTemplate.put("4", "value");
	}
	
	@Test
	public void testExecute() {
		Map<String, String> localDocTemplate = new LinkedHashMap<>();
		localDocTemplate.put("2", "value");
		this.nestedCommander.execute(this.fragment, localDocTemplate, "2");
		assertTrue(localDocTemplate.size() == 1);
	}

	@Test
	public void testExecute_SomeElements() {
		this.nestedCommander.execute(this.fragment, this.docTemplate, "2");
		assertTrue(this.docTemplate.size() == 4 &&
				!this.docTemplate.containsKey("2") &&
				this.docTemplate.containsKey("10") &&
				this.docTemplate.containsKey("1") &&
				this.docTemplate.containsKey("3") &&
				this.docTemplate.containsKey("4"));
	}
}
