package com.asra.developer.common.error;

public class UnAuthorizedException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    String message;

    public UnAuthorizedException() {
        super();
    }

    public UnAuthorizedException(String message) {
        super(message);
        this.message = message;
    }
}