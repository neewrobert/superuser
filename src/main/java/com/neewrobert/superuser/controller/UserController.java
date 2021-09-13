package com.neewrobert.superuser.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neewrobert.superuser.dto.UserDTO;
import com.neewrobert.superuser.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api(value = "SuperUser - Users")
public class UserController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8624396592142351786L;
	@Autowired
	UserService userService;

	@ApiOperation(value = "Create a new user")
	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDto) {

		userService.createUser(userDto);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

}
