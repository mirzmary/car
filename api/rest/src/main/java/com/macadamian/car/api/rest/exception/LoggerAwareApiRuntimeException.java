package com.macadamian.car.api.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerAwareApiRuntimeException extends ApiRuntimeException {

    private static final long serialVersionUID = -4188822598060379544L;

    //region Properties
    protected final Logger LOGGER;
    //endregion

    //region Constructors
    public LoggerAwareApiRuntimeException(final Object caller, final String message, final Object... args) {
        super(String.format(message, args));
        final String errorMessage = String.format(message, args);
        this.LOGGER = LoggerFactory.getLogger(caller.getClass());
        this.LOGGER.error(errorMessage);
    }

    public LoggerAwareApiRuntimeException(final Object caller, final String message, final Throwable cause, final Object... args) {
        super(String.format(message, args), cause);
        final String errorMessage = String.format(message, args);
        this.LOGGER = LoggerFactory.getLogger(caller.getClass());
        this.LOGGER.error(errorMessage);
    }
    //endregion
}
