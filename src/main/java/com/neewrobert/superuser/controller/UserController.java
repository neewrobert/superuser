package com.neewrobert.superuser.controller;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neewrobert.superuser.dto.UserDTO;
import com.neewrobert.superuser.model.User;
import com.neewrobert.superuser.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
@ExposesResourceFor(User.class)
@Api(value = "SuperUser - Users")
public class UserController implements Serializable {

	private static final long serialVersionUID = -8624396592142351786L;
	@Autowired
	UserService userService;

	@ApiOperation(value = "Create a new user")
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto) {

		UserDTO saved = userService.save(userDto);

		return new ResponseEntity<UserDTO>(saved, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update User")
	@PutMapping("/{email}")
	public ResponseEntity<String> updateUser(@Valid @RequestBody UserDTO userDto, @PathVariable @Email String email) {

		userService.merge(userDto, email);

		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

	@ApiOperation(value = "Get User")
	@GetMapping("/{email}")
	public ResponseEntity<UserDTO> getUser(@PathVariable @Email String email) {

		UserDTO userDTO = userService.getUserByEmail(email);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);

	}

	@ApiOperation(value = "Get All Users")
	@GetMapping(produces = { "application/hal+json" })
	public ResponseEntity<Page<UserDTO>> getAllUsers(
			@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size) {

		Page<UserDTO> users = userService.getAllUsers(page, size);

		if (users.isEmpty()) {
			return new ResponseEntity<Page<UserDTO>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Page<UserDTO>>(users, HttpStatus.OK);

	}

}
