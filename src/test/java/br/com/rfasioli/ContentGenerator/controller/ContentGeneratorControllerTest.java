package br.com.rfasioli.ContentGenerator.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.rfasioli.ContentGenerator.service.ContentGeneratorService;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ContentGeneratorControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ContentGeneratorService contentGeneratorService;

	@Test
	public void testGenerate() throws Exception {
		given(this.contentGeneratorService.generateContent("{ \"code\": 10 }"))
				.willReturn("Text with code: 10");
		this.mvc.perform(get("/contents").accept(MediaType.ALL))
				.andExpect(status().isOk());
	}

	@Test
	public void testGeneratePdf() throws Exception {
		given(this.contentGeneratorService.generatePdfReport("{ \"code\": 10 }"))
				.willReturn(null);
		this.mvc.perform(get("/contents/pdf").accept(MediaType.ALL))
				.andExpect(status().isOk());
	}

}
