package com.neewrobert.superuser.controller.handlers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.neewrobert.superuser.controller.UserController;
import com.neewrobert.superuser.controller.exception.OperationException;
import com.neewrobert.superuser.controller.exception.UserAlreadyExistsException;
import com.neewrobert.superuser.controller.exception.UserNotFoundException;
import com.neewrobert.superuser.dto.ErrorDto;

@RestControllerAdvice
public class UserHandler extends RestResponseEntityHandler {

	@ExceptionHandler(value = UserNotFoundException.class)
	protected ResponseEntity<ErrorDto> handler(UserNotFoundException ex, WebRequest request) {

		ErrorDto errorDto = new ErrorDto(ex.getMessage(), null, null);
		Link link = linkTo(UserController.class).withSelfRel();
		errorDto.add(link);

		return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = UserAlreadyExistsException.class)
	protected ResponseEntity<ErrorDto> handlerUserAlreadyExists(UserAlreadyExistsException ex, WebRequest request) {
		ErrorDto errorDto = new ErrorDto(ex.getMessage(), null, null);
		Link link = linkTo(methodOn(UserController.class).createUser(ex.getUserDto())).withSelfRel();
		errorDto.add(link);
		
		return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.CONFLICT);

	}
	
	@ExceptionHandler(value = OperationException.class)
	protected ResponseEntity<ErrorDto> handlerOperationException(OperationException ex, WebRequest request) {
		ErrorDto errorDto = new ErrorDto(ex.getMessage(), null, null);

		return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);

	}

}
