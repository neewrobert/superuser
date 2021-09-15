package com.neewrobert.superuser.controller.exception;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -574052407830898279L;

	public UserNotFoundException(String email) {

		super(MessageFormat.format("User {0} not found", email));

	}

	

}
