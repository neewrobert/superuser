package com.neewrobert.superuser.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neewrobert.superuser.model.Profile;
import com.neewrobert.superuser.repository.ProfileRepository;

@Service
public class ProfileService {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ProfileRepository profileRepository;
	
	@Transactional
	public Optional<Profile> findProfileByType(String profileType) {
		
			Optional<Profile> profile = profileRepository.findProfileByType(profileType);
			return profile;
		
	}

}
