package com.neewrobert.superuser.controller.exception;

import java.text.MessageFormat;

import com.neewrobert.superuser.dto.UserDTO;

public class UserAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 555041142296029463L;

	private UserDTO userDto;

	public UserAlreadyExistsException(UserDTO userDto) {
		super(MessageFormat.format("User {0} already exists", userDto.getEmail()));

		this.userDto = userDto;
	}

	/**
	 * @return the userDto
	 */
	public UserDTO getUserDto() {
		return userDto;
	}

}
