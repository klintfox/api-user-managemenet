package com.bci.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedAccessException extends RuntimeException {
    private static final long serialVersionUID = 135792468L;

    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
