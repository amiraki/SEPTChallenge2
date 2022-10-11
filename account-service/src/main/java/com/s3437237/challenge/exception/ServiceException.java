package com.s3437237.challenge.exception;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ServiceException(String exception) {
        super(exception);
    }
}
