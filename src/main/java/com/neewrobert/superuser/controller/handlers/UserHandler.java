package com.neewrobert.superuser.controller.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.neewrobert.superuser.controller.exception.ProfileNotFoundException;
import com.neewrobert.superuser.controller.exception.UserAlreadyExistsException;
import com.neewrobert.superuser.controller.exception.UserNotFoundException;
import com.neewrobert.superuser.dto.ErrorDto;

public class UserHandler extends RestResponseEntityHandler {
	
	@ExceptionHandler(value = {UserNotFoundException.class, ProfileNotFoundException.class})
	protected ResponseEntity<ErrorDto> handler(RuntimeException ex, WebRequest request) {

		return new ResponseEntity<ErrorDto>(new ErrorDto(ex.getMessage(), null, null), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = UserAlreadyExistsException.class)
	protected ResponseEntity<ErrorDto> handlerUserAlreadyExists(UserAlreadyExistsException ex, WebRequest request) {
		ErrorDto errorDto = new ErrorDto(ex.getMessage(), null, null);

		errorDto.add(ex.getUserDto().getLinks());
		return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);

	}

}
