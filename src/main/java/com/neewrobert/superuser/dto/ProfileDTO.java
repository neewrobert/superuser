package com.neewrobert.superuser.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

public class ProfileDTO extends RepresentationModel<ProfileDTO> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6127368895546006824L;

	private Long id;
	
	@NotBlank
	private String profileType;

	/**
	 * @param profileType
	 */
	public ProfileDTO(String profileType) {
		this.profileType = profileType;
	}

	public ProfileDTO() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the profileType
	 */
	public String getProfileType() {
		return profileType;
	}

	/**
	 * @param profileType the profileType to set
	 */
	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

}
