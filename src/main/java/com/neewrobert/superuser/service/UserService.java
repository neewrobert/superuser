package com.neewrobert.superuser.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neewrobert.superuser.assembler.UserAssembler;
import com.neewrobert.superuser.controller.exception.OperationExceptionBuilder;
import com.neewrobert.superuser.controller.exception.UserAlreadyExistsException;
import com.neewrobert.superuser.controller.exception.UserNotFoundException;
import com.neewrobert.superuser.dto.ProfileDTO;
import com.neewrobert.superuser.dto.UserDTO;
import com.neewrobert.superuser.mapper.UserMapper;
import com.neewrobert.superuser.model.Profile;
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

	@Autowired
	UserAssembler userAssembler;

	@Autowired
	OperationExceptionBuilder exceptionBuilder;

	@Autowired
	PagedResourcesAssembler<User> pagedResourcesAssembler;

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

		return userAssembler.toModel(user);

	}

	public void deleteByEmail(String email) {

		UserDTO found = this.getUserByEmail(email);

		userRepository.deleteById(found.getId());

	}

	@Transactional
	public UserDTO merge(@Valid UserDTO dto, @Email String email) {

		User foundUser = userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

		// it's not possible to change an email thats already has been using by another
		// user
		if(!dto.getEmail().equals(email)) {
			userRepository.findUserByEmail(dto.getEmail()).ifPresent(u -> {throw exceptionBuilder.build("exception.msg.email.notpermited");});
		}
		

		ProfileDTO profileDto = dto.getProfile();

		if (profileDto != null) {

			ProfileDTO foundProfile = profileService.findProfileByType(profileDto.getProfileType());
			foundUser.setProfile(modelMapper.map(foundProfile, Profile.class));

		}

		UserMapper.mapDtoToEntityNonNullfields(dto, foundUser);
		return userAssembler.toModel(userRepository.save(foundUser));

	}

	public UserDTO getUserByEmail(@Email String email) {

		User userFound = userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

		return userAssembler.toModel(userFound);

	}

	public PagedModel<UserDTO> getAllUsers(int page, int size) {

		Pageable paging = PageRequest.of(page, size);

		Page<User> users = userRepository.findAll(paging);

		return pagedResourcesAssembler.toModel(users, userAssembler);
	}

	public CollectionModel<UserDTO> getAllUsersByProfile(String profileType) {
		
		Optional<List<User>> usersByProfile = userRepository.findAllUsersByProfile(profileType);

		return userAssembler.toCollectionModel(usersByProfile.get());
	}

}
