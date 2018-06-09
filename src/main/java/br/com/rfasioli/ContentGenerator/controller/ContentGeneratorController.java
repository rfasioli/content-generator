package br.com.rfasioli.ContentGenerator.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rfasioli.ContentGenerator.exception.MissingParameterException;
import br.com.rfasioli.ContentGenerator.exception.PdfGenerationException;
import br.com.rfasioli.ContentGenerator.service.ContentGeneratorService;

/**
 * @author rfasioli
 *
 */
@CrossOrigin(origins="*")
@RestController
@RequestMapping(path = "/contents", produces="application/json")
public class ContentGeneratorController {
	
	@Autowired
	private transient ContentGeneratorService rptGenSvc;
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public String generate(@RequestParam String source) throws MissingParameterException {
		String response = rptGenSvc.generateContent(source);
		return response;
	}
	
	@RequestMapping(path = "pdf", method = RequestMethod.GET)
	public byte[] generatePdf(@RequestParam String source) throws MissingParameterException, IOException, PdfGenerationException {
		byte[] response = rptGenSvc.generatePdfReport(source);
		return response;
	}

	public ContentGeneratorController() {
		super();
	}
}
