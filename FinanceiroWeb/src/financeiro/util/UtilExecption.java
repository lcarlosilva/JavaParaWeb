package financeiro.util;

public class UtilExecption extends Exception {

	private static final long serialVersionUID = 8011560432922796632L;

	public UtilExecption() {

	}

	public UtilExecption(String message) {
		super(message);
	}

	public UtilExecption(Throwable cause) {
		super(cause);
	}

	public UtilExecption(String message, Throwable cause) {
		super(message, cause);
	}

	public UtilExecption(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
