package com.bci.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceConflictException extends RuntimeException {
    private static final long serialVersionUID = 987654321L;

    public ResourceConflictException(String message) {
        super(message);
    }
}