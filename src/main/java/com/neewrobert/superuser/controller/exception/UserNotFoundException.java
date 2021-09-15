package com.neewrobert.superuser.controller.exception;

import java.text.MessageFormat;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -574052407830898279L;

	public UserNotFoundException(String email) {

		super(MessageFormat.format("User {0} not found", email));

	}

}
