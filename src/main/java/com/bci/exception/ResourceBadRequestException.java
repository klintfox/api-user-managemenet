package com.bci.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceBadRequestException extends RuntimeException {
	private static final long serialVersionUID = 292317171077184479L;

	public ResourceBadRequestException(String message) {
		super(message);
	}

}