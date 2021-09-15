package com.neewrobert.superuser.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.neewrobert.superuser.controller.UserController;
import com.neewrobert.superuser.dto.UserDTO;
import com.neewrobert.superuser.model.User;

@Component
public class UserAssembler  extends RepresentationModelAssemblerSupport<User, UserDTO>{
	
	

	@Autowired
	ModelMapper modelMapper;
	
	public UserAssembler() {
		super(UserController.class, UserDTO.class);
	}

	@Override
	public UserDTO toModel(User user) {
		
		
		UserDTO dto = modelMapper.map(user, UserDTO.class);
		dto.add(linkTo(methodOn(UserController.class).getUser(user.getEmail())).withSelfRel());
		
		return dto;
	}
	
	@Override
	public CollectionModel<UserDTO> toCollectionModel(Iterable<? extends User> entities) 
	{
		CollectionModel<UserDTO> Users = super.toCollectionModel(entities);
		
		return Users;
	}

}
