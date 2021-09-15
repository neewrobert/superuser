package com.neewrobert.superuser.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.neewrobert.superuser.controller.ProfileController;

public class ProfileDTO extends RepresentationModel<ProfileDTO> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6127368895546006824L;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long id;
	
	@NotBlank
	private String profileType;

	/**
	 * @param profileType
	 */
	public ProfileDTO(String profileType) {
		this.profileType = profileType;
		selfLink();
	}

	public ProfileDTO() {
	}
	
	private void selfLink() {
		
		Link link = linkTo(methodOn(ProfileController.class).getProfile(this.getProfileType())).withSelfRel();
		this.add(link);
	
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
		selfLink();
	}

}
