package com.neewrobert.superuser.mapper;

import com.neewrobert.superuser.dto.UserDTO;
import com.neewrobert.superuser.model.User;

public class UserMapper {
	
	/**
	 * Map all fields from source to destination, except null fields from source object
	 * @param DTO source
	 * @param Entity destination
	 * @return
	 */
	public static void mapDtoToEntityNonNullfields(UserDTO source, User destination) {
		
		destination.setName(source.getName() != null ? source.getName() : destination.getName());
		destination.setBirthDate(source.getBirthDate() != null  ? source.getBirthDate() : destination.getBirthDate());
		destination.setEmail(source.getEmail() != null ? source.getEmail() : destination.getEmail());
		destination.setPhoneNumber(source.getPhoneNumber() != null ? source.getPhoneNumber() : destination.getPhoneNumber());
	}

}
