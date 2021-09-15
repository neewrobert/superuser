package com.neewrobert.superuser.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neewrobert.superuser.dto.ProfileDTO;
import com.neewrobert.superuser.model.Profile;
import com.neewrobert.superuser.service.ProfileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/profiles")
@ExposesResourceFor(Profile.class)
@Api(value = "SuperUser - Profile")
public class ProfileController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4441295909500215681L;
	
	@Autowired
	ProfileService profileService;

	@ApiOperation(value = "Create a new Profile")
	@PostMapping
	public ResponseEntity<ProfileDTO> createProfile(@Valid @RequestBody ProfileDTO profile){
		
		ProfileDTO saved = profileService.createProfile(profile);
		
		return new ResponseEntity<ProfileDTO>(saved, HttpStatus.CREATED);
		
	}

	@ApiOperation(value = "Get a Profile")
	@GetMapping( "/{profileType}")
	public ResponseEntity<ProfileDTO> getProfile(String profileType) {
		
		ProfileDTO found = profileService.findProfileByType(profileType);
		
		
		return new ResponseEntity<ProfileDTO>(found, HttpStatus.OK);
	}

}
