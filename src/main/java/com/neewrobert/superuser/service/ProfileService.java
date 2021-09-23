package com.neewrobert.superuser.service;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.neewrobert.superuser.assembler.ProfileAssembler;
import com.neewrobert.superuser.controller.exception.OperationExceptionBuilder;
import com.neewrobert.superuser.controller.exception.ProfileAlreadyExistsException;
import com.neewrobert.superuser.controller.exception.ProfileNotFoundException;
import com.neewrobert.superuser.dto.ProfileDTO;
import com.neewrobert.superuser.model.Profile;
import com.neewrobert.superuser.repository.ProfileRepository;

@Service
public class ProfileService {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ProfileAssembler profileAssembler;
	
	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OperationExceptionBuilder exceptionBuilder;
	
	@Autowired
	private PagedResourcesAssembler<Profile> pagedResourcesAssembler;
	
	
	public ProfileDTO findProfileByType(String profileType) {
		
		Profile found = profileRepository.findProfileByType(profileType).orElseThrow(() -> new ProfileNotFoundException(profileType));
		
		
		return profileAssembler.toModel(found);
		
	}

	public ProfileDTO createProfile(@Valid ProfileDTO profile) {
		
		profileRepository.findProfileByType(profile.getProfileType()).ifPresent(s -> {throw new ProfileAlreadyExistsException(profile);});
		
		Profile saved = profileRepository.save(modelMapper.map(profile, Profile.class));
		
		return profileAssembler.toModel(saved);
	}

	public PagedModel<ProfileDTO> getAllProfiles(Pageable paging) {
		
		Page<Profile> profiles = profileRepository.findAll(paging);

		return pagedResourcesAssembler.toModel(profiles, profileAssembler);
	}

	public void deleteByType(String profileType) {
		
		ProfileDTO found = this.findProfileByType(profileType);
		
		if(!userService.getAllUsersByProfile(profileType).getContent().isEmpty()) {
			throw exceptionBuilder.build("exception.msg.profile.inuse");
		}
		
		profileRepository.deleteById(found.getId());
		
	}

}
