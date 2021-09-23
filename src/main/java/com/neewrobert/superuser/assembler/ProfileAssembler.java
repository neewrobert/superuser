package com.neewrobert.superuser.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Service;

import com.neewrobert.superuser.controller.ProfileController;
import com.neewrobert.superuser.dto.ProfileDTO;
import com.neewrobert.superuser.model.Profile;

@Service
public class ProfileAssembler  extends RepresentationModelAssemblerSupport<Profile, ProfileDTO>{
	
	

	@Autowired
	ModelMapper modelMapper;
	
	public ProfileAssembler() {
		super(ProfileController.class, ProfileDTO.class);
	}

	@Override
	public ProfileDTO toModel(Profile profile) {
		
		
		ProfileDTO dto = modelMapper.map(profile, ProfileDTO.class);
		dto.add(linkTo(methodOn(ProfileController.class).getProfile(profile.getProfileType())).withSelfRel());
		return dto;
	}
	
	@Override
	public CollectionModel<ProfileDTO> toCollectionModel(Iterable<? extends Profile> entities) 
	{
		CollectionModel<ProfileDTO> profiles = super.toCollectionModel(entities);
		
		
		return profiles;
	}

}
