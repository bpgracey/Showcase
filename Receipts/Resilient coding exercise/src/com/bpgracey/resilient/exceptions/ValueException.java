/**
 * 
 */
package com.bpgracey.resilient.exceptions;

/**
 * Exception in Value generation
 * 
 * @author Ban
 *
 */
public class ValueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValueException() {
		this("Invalid value");
	}

	public ValueException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValueException(String message) {
		super(message);
	}

	public ValueException(Throwable cause) {
		super(cause);
	}

}
