package com.neewrobert.superuser.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserDTO extends RepresentationModel<UserDTO> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1307634754249026896L;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long id;

	@NotBlank
	private String name;

	@Past
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	@JsonDeserialize(using = LocalDateDeserializer.class, as = LocalDate.class)
	@JsonSerialize(using = LocalDateSerializer.class, as = LocalDate.class)
	private LocalDate birthDate;

	private ProfileDTO profile;

	@NotBlank
	private String phoneNumber;

	@NotBlank
	@Email
	private String email;

	/**
	 * @param name
	 * @param birthDate
	 * @param profileType
	 * @param phoneNumber
	 * @param email
	 */
	public UserDTO(@NotBlank String name, @NotBlank @Past LocalDate birthDate, ProfileDTO profile,
			@NotBlank String phoneNumber, @NotBlank @Email String email) {
		this.email = "";
		this.name = name;
		this.birthDate = birthDate;
		this.profile = profile;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	/**
	 * 
	 */
	public UserDTO() {
	}
	
	public String toJson() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the birthDate
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the profile
	 */
	public ProfileDTO getProfile() {
		return profile;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(ProfileDTO profile) {
		this.profile = profile;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
