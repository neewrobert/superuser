package com.neewrobert.superuser.service;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	ProfileRepository profileRepository;
	
	public ProfileDTO findProfileByType(String profileType) {
		
		Profile found = profileRepository.findProfileByType(profileType).orElseThrow(() -> new ProfileNotFoundException(profileType));
		
		return modelMapper.map(found, ProfileDTO.class);
		
	}

	public ProfileDTO createProfile(@Valid ProfileDTO profile) {
		
		profileRepository.findProfileByType(profile.getProfileType()).ifPresent(s -> {throw new ProfileAlreadyExistsException(profile);});
		
		Profile saved = profileRepository.save(modelMapper.map(profile, Profile.class));
		
		return modelMapper.map(saved, ProfileDTO.class);
	}

}
