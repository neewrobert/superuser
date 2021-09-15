package com.neewrobert.superuser.controller.handlers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.neewrobert.superuser.controller.ProfileController;
import com.neewrobert.superuser.controller.exception.ProfileAlreadyExistsException;
import com.neewrobert.superuser.controller.exception.ProfileNotFoundException;
import com.neewrobert.superuser.dto.ErrorDto;
import com.neewrobert.superuser.dto.ProfileDTO;

@RestControllerAdvice
public class ProfileHandler extends RestResponseEntityHandler {

	@ExceptionHandler(value = ProfileNotFoundException.class)
	protected ResponseEntity<ErrorDto> handler(ProfileNotFoundException ex, WebRequest request) {
		
		ErrorDto errorDto = new ErrorDto(ex.getMessage(), "profile", "profileType");
		Link link = linkTo(methodOn(ProfileController.class).createProfile(new ProfileDTO())).withSelfRel();
		errorDto.add(link);

		return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = ProfileAlreadyExistsException.class)
	protected ResponseEntity<ErrorDto> handlerUserAlreadyExists(ProfileAlreadyExistsException ex, WebRequest request) {
		ErrorDto errorDto = new ErrorDto(ex.getMessage(), null, null);

		errorDto.add(ex.getProfile().getLinks());

		return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.CONFLICT);

	}
}
