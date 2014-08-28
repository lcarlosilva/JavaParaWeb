package financeiro.util;

public class DAOException extends Exception {

	private static final long serialVersionUID = 5491788874479318930L;

	public DAOException() {

	}

	public DAOException(String message) {
		super(message);
	
	}

	public DAOException(Throwable cause) {
		super(cause);

	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);

	}

	public DAOException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
