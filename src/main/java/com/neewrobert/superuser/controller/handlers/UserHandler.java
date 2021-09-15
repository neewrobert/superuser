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
import com.neewrobert.superuser.controller.exception.UserAlreadyExistsException;
import com.neewrobert.superuser.controller.exception.UserNotFoundException;
import com.neewrobert.superuser.dto.ErrorDto;
import com.neewrobert.superuser.dto.UserDTO;

@RestControllerAdvice
public class UserHandler extends RestResponseEntityHandler {

	@ExceptionHandler(value = UserNotFoundException.class)
	protected ResponseEntity<ErrorDto> handler(UserNotFoundException ex, WebRequest request) {

		ErrorDto errorDto = new ErrorDto(ex.getMessage(), null, null);
		Link link = linkTo(methodOn(UserController.class).createUser(new UserDTO())).withSelfRel();
		errorDto.add(link);

		return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = UserAlreadyExistsException.class)
	protected ResponseEntity<ErrorDto> handlerUserAlreadyExists(UserAlreadyExistsException ex, WebRequest request) {
		ErrorDto errorDto = new ErrorDto(ex.getMessage(), null, null);

		errorDto.add(ex.getUserDto().getLinks());
		return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.CONFLICT);

	}

}
