package com.neewrobert.superuser.controller.exception;

import java.text.MessageFormat;

import com.neewrobert.superuser.dto.ProfileDTO;

public class ProfileAlreadyExistsException extends RuntimeException {

	private ProfileDTO profile;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5791417260214462371L;

	public ProfileAlreadyExistsException(ProfileDTO profile) {

		super(MessageFormat.format("Profile {0} already exists", profile.getProfileType()));

		this.profile = profile;

	}

	/**
	 * @return the profile
	 */
	public ProfileDTO getProfile() {
		return profile;
	}

}
