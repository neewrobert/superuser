package com.neewrobert.superuser.controller.exception;

import java.text.MessageFormat;

public class ProfileNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6753075237575945751L;
	
	
	public ProfileNotFoundException(String profileType) {
		
		super(MessageFormat.format("Profile {0} does not exists", profileType));
	}

}
