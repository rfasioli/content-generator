package br.com.rfasioli.ContentGenerator.exception;

/**
 * @author adurazzo
 *
 */
public class PdfGenerationException extends Exception {

	private static final long serialVersionUID = -287643373501572608L;

	public PdfGenerationException() {
		super();
	}

	public PdfGenerationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PdfGenerationException(String message, Throwable cause) {
		super(message, cause);
	}

	public PdfGenerationException(String message) {
		super(message);
	}

	public PdfGenerationException(Throwable cause) {
		super(cause);
	}

}
