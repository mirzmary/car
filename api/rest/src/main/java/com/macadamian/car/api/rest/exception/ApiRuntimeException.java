package com.macadamian.car.api.rest.exception;


public class ApiRuntimeException extends RuntimeException {

    public ApiRuntimeException() {
        super();
    }

    public ApiRuntimeException(final String message) {
        super(message);
    }

    public ApiRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }
}