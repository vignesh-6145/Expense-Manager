package com.vignesh.ExpenseManager.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class InvalidActionException extends RuntimeException {
	public InvalidActionException(String message) {
		super(message);
	}
}
