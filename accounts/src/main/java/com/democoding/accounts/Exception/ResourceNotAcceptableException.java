package com.democoding.accounts.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)

public class ResourceNotAcceptableException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceNotAcceptableException() {
        super();
    }

    public ResourceNotAcceptableException(String message) {
        super(message);
    }

    public ResourceNotAcceptableException(String message, Throwable cause) {
        super(message, cause);
    }
}
