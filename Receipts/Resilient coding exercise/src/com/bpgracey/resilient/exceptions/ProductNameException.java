/**
 * 
 */
package com.bpgracey.resilient.exceptions;

/**
 * Exception in Product genberation
 * 
 * @author Ban
 *
 */
public class ProductNameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNameException() {
		this("Invalid product name");
	}

	public ProductNameException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductNameException(String message) {
		super(message);
	}

	public ProductNameException(Throwable cause) {
		super(cause);
	}

}
