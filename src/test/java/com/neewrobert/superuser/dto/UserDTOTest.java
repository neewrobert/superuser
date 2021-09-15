package com.neewrobert.superuser.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.neewrobert.superuser.model.Profile;
import com.neewrobert.superuser.model.User;

public class UserDTOTest {
	
	private ModelMapper modelMapper = new ModelMapper();
	
	
	@Test
	public void testMapperToDTO() {
		
		Profile profile = new Profile();
		profile.setProfileType("manager");
		
		User entity = new User("John Doe", LocalDate.of(1991, 07, 28), profile, "987411289", "johndoe@gmail.com");
		
		UserDTO dto = modelMapper.map(entity, UserDTO.class);
		
		assertEquals(dto.getName(), entity.getName());
		assertEquals(dto.getBirthDate(), entity.getBirthDate());
		assertEquals(dto.getProfile().getProfileType(), entity.getProfile().getProfileType());
		assertEquals(dto.getPhoneNumber(), entity.getPhoneNumber());
		assertEquals(dto.getEmail(), entity.getEmail());
		
	}
	
	@Test
	public void testMapperToEntity() {
		
		Profile profile = new Profile();
		profile.setProfileType("manager");
		UserDTO dto = new UserDTO("John Doe", LocalDate.of(1991, 07, 28), new ProfileDTO( "manager"), "987411289", "johndoe@gmail.com");
		
		User entity = modelMapper.map(dto, User.class);
		
		assertEquals(entity.getName(), dto.getName());
		assertEquals(entity.getBirthDate(), dto.getBirthDate());
		assertEquals(entity.getProfile().getProfileType(), dto.getProfile().getProfileType());
		assertEquals(entity.getPhoneNumber(), dto.getPhoneNumber());
		assertEquals(entity.getEmail(), dto.getEmail());
		
	}

}
