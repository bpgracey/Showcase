package com.bpgracey.resilient.exceptions;

public class ProductLineException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductLineException() {
		super("Invalid product line");
	}
	
	public ProductLineException(String msg) {
		super(msg);
	}
}
