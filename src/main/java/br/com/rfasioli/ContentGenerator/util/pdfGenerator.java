package br.com.rfasioli.ContentGenerator.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import br.com.rfasioli.ContentGenerator.exception.PdfGenerationException;

public class pdfGenerator {
	
	/*
	 * fromXHtmlContent - Generates and convert to Base64 a PDF File from XHTML Content
	 * @param xHtmlContent - String of XHTML content
	 * @return - byte[] Base 64 Bytes of generated PDF file
	 */
	public static byte[] fromXHtmlContentToBase64(String xHtmlContent) throws IOException, PdfGenerationException {
		byte[] bFile = Base64.encodeBase64(fromXHtmlContent(xHtmlContent));
		return bFile;
	}
	
	/*
	 * fromXHtmlContent - Generates a binary PDF File from XHTML Content
	 * @param xHtmlContent - String of XHTML content
	 * @return - byte[] Bytes of generated PDF file
	 */
	public static byte[] fromXHtmlContent(String xHtmlContent) throws IOException, PdfGenerationException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PdfRendererBuilder builder = new PdfRendererBuilder();
		
		try {
			builder.withHtmlContent(xHtmlContent, "");
			builder.toStream(os);
			builder.run();
		}
		catch (Exception ex) {
			throw new PdfGenerationException(ex);
		}
		
		byte[] bFile = os.toByteArray();
		os.close();
		
		return bFile;
	}
	
}
