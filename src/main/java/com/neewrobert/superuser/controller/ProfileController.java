package com.neewrobert.superuser.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neewrobert.superuser.dto.ProfileDTO;
import com.neewrobert.superuser.service.ProfileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@ExposesResourceFor(ProfileDTO.class)
@Api(value = "SuperUser - Profile")
public class ProfileController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4441295909500215681L;

	@Autowired
	ProfileService profileService;

	@ApiOperation(value = "Create a new Profile")
	@PostMapping("/profiles")
	public ResponseEntity<ProfileDTO> createProfile(@Valid @RequestBody ProfileDTO profile) {

		ProfileDTO saved = profileService.createProfile(profile);

		return new ResponseEntity<ProfileDTO>(saved, HttpStatus.CREATED);

	}

	@ApiOperation(value = "Get a Profile")
	@GetMapping("/profiles/{profileType}")
	public ResponseEntity<ProfileDTO> getProfile(@PathVariable String profileType) {

		ProfileDTO found = profileService.findProfileByType(profileType);

		return new ResponseEntity<ProfileDTO>(found, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete a Profile")
	@DeleteMapping("/profiles/{profileType}")
	public ResponseEntity<String> deleteProfile(@PathVariable String profileType) {

		profileService.deleteByType(profileType);

		return  ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Get All Profiles")
	@GetMapping(path = "/profiles", produces = { "application/hal+json" })
	public ResponseEntity<PagedModel<ProfileDTO>> getAllProfiles(Pageable paging) {

		PagedModel<ProfileDTO> profiles = profileService.getAllProfiles(paging);

		if (profiles.getMetadata().getTotalElements() == 0) {
			return new ResponseEntity<PagedModel<ProfileDTO>>(HttpStatus.NO_CONTENT);
		}

		return ResponseEntity
				.ok()
				.contentType(MediaTypes.HAL_JSON)
				.body(profiles);

	}
	
	

}
