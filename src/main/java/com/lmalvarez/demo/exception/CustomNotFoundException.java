package com.lmalvarez.demo.exception;

public class CustomNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7559104374778048386L;

	public CustomNotFoundException(String message) {
		super(message);
	}
	
	public CustomNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
