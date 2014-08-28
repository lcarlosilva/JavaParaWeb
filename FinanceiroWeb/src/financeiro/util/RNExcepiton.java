package financeiro.util;

public class RNExcepiton extends Exception {

	private static final long serialVersionUID = -5660700023109261838L;

	public RNExcepiton() {

	}

	public RNExcepiton(String message) {
		super(message);

	}

	public RNExcepiton(Throwable cause) {
		super(cause);
	}

	public RNExcepiton(String message, Throwable cause) {
		super(message, cause);
	}

	public RNExcepiton(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
