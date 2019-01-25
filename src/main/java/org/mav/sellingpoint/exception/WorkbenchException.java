package org.mav.sellingpoint.exception;

public class WorkbenchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WorkbenchException(String message) {
		super(message);
	}

	public WorkbenchException(String message, Throwable ex) {
		super(message, ex);
	}

	public WorkbenchException() {
		super();
	}
}
