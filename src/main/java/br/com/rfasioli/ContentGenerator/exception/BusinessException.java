package br.com.rfasioli.ContentGenerator.exception;

/**
 * @author adurazzo
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = -287643373501572608L;

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

}
