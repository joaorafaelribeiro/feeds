package util.exceptions;

import play.Logger;

public class FeedException extends Exception{

	public FeedException() {
		super();
		Logger.error("Error Geral");
	}

	public FeedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		Logger.error(message);
	}

	public FeedException(String message, Throwable cause) {
		super(message, cause);
		Logger.error(message);
	}

	public FeedException(String message) {
		super(message);
		Logger.error(message);
	}

	public FeedException(Throwable cause) {
		super(cause);
		Logger.error(cause.getMessage());
	}

}
