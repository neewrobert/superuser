package com.neewrobert.superuser.controller;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neewrobert.superuser.dto.UserDTO;
import com.neewrobert.superuser.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@ExposesResourceFor(UserDTO.class)
@Api(value = "SuperUser - Users")
public class UserController implements Serializable {

	private static final long serialVersionUID = -8624396592142351786L;
	@Autowired
	UserService userService;

	@ApiOperation(value = "Create a new user")
	@PostMapping("/users")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto) {

		UserDTO saved = userService.save(userDto);

		return new ResponseEntity<UserDTO>(saved, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update User")
	@PutMapping("users/{email}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto, @PathVariable @Email String email) {

		UserDTO updated = userService.merge(userDto, email);

		return new ResponseEntity<UserDTO>(updated, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get User")
	@GetMapping("users/{email}")
	public ResponseEntity<UserDTO> getUser(@PathVariable @Email String email) {

		UserDTO userDTO = userService.getUserByEmail(email);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);

	}
	
	@ApiOperation(value = "Delete an User by its email")
	@DeleteMapping("users/{email}")
	public ResponseEntity<String> deleteProfileUser(@PathVariable @Email String email) {

		userService.deleteByEmail(email);

		return  ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Get All Users")
	@GetMapping(path="/users", produces = { "application/hal+json" })
	public ResponseEntity<PagedModel<UserDTO>> getAllUsers(
			@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size) {

		PagedModel<UserDTO> users = userService.getAllUsers(page, size);

		if (users.getMetadata().getTotalElements() == 0) {
			return new ResponseEntity<PagedModel<UserDTO>>(HttpStatus.NO_CONTENT);
		}

		return ResponseEntity
				.ok()
				.contentType(MediaTypes.HAL_JSON)
				.body(users);

	}
	
	@ApiOperation(value = "Get All Users by their profile")
	@GetMapping(path="/profiles/{profileType}/users", produces = { "application/hal+json" })
	public ResponseEntity<CollectionModel<UserDTO>> getUsersByProfiles(
			@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size,
	        @PathVariable String profileType) {
		

		CollectionModel<UserDTO> users = userService.getAllUsersByProfile(profileType);

		if (users.getContent().isEmpty()) {
			return new ResponseEntity<CollectionModel<UserDTO>>(HttpStatus.NO_CONTENT);
		}

		return ResponseEntity
				.ok()
				.contentType(MediaTypes.HAL_JSON)
				.body(users);

	}

}
