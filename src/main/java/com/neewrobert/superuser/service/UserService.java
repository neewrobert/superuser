package com.neewrobert.superuser.service;

import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neewrobert.superuser.controller.exception.UserAlreadyExistsException;
import com.neewrobert.superuser.controller.exception.UserNotFoundException;
import com.neewrobert.superuser.dto.ProfileDTO;
import com.neewrobert.superuser.dto.UserDTO;
import com.neewrobert.superuser.model.User;
import com.neewrobert.superuser.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProfileService profileService;

	@Transactional
	public UserDTO save(UserDTO dto) {

		userRepository.findUserIdByEmail(dto.getEmail()).ifPresent(s -> {
			throw new UserAlreadyExistsException(dto);
		});
		ProfileDTO profileDto = dto.getProfile();

		if (profileDto != null) {

			ProfileDTO foundProfile = profileService.findProfileByType(profileDto.getProfileType());
			
			profileDto.setId(foundProfile.getId());

		}

		User user = userRepository.save(modelMapper.map(dto, User.class));

		return modelMapper.map(user, UserDTO.class);

	}

	public void merge(@Valid UserDTO dto, @Email String email) {

		Long foundId = userRepository.findUserIdByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

		User user = modelMapper.map(dto, User.class);
		user.setId(foundId);
		userRepository.save(user);

	}

	public UserDTO getUserByEmail(@Email String email) {

		User userFound = userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

		return modelMapper.map(userFound, UserDTO.class);

	}

	public Page<UserDTO> getAllUsers(int page, int size) {

		Pageable paging = PageRequest.of(page, size);

		Page<User> users = userRepository.findAll(paging);

		return new PageImpl<UserDTO>(
				users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList()));
	}

}
