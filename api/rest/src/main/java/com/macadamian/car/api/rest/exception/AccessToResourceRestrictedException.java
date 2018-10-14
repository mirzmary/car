package com.macadamian.car.api.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Permission Denied", code = HttpStatus.FORBIDDEN)
public class AccessToResourceRestrictedException extends  LoggerAwareApiRuntimeException {
    private static final long serialVersionUID = -8823095429513116891L;

    public AccessToResourceRestrictedException(final Object caller, final String message, final Object... args){
        super(caller, message, args);
    }
}
