package com.neewrobert.superuser.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neewrobert.superuser.dto.UserDTO;
import com.neewrobert.superuser.model.User;
import com.neewrobert.superuser.repository.UserRepository;

@Service
public class UserService {
	
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserRepository userRepository;
	
	
	public void createUser(UserDTO dto) {
		
		userRepository.save(modelMapper.map(dto, User.class));
		
	}

}
