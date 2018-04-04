package br.com.rfasioli.ContentGenerator.exception;

/**
 * @author adurazzo
 *
 */
public class MissingParameterException extends Exception {

	private static final long serialVersionUID = -4822730835714967773L;

	public MissingParameterException() {
		super();
	}

	public MissingParameterException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MissingParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public MissingParameterException(String message) {
		super(message);
	}

	public MissingParameterException(Throwable cause) {
		super(cause);
	}

}
