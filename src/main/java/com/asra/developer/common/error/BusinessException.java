package com.asra.developer.common.error;

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	String message;

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
		this.message = message;
	}
}
