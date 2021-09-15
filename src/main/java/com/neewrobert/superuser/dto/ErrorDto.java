package com.neewrobert.superuser.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

public class ErrorDto extends RepresentationModel<ErrorDto>implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1429564096883619361L;

	private final String message;
	private final String field;
	private final Object parameter;
	
	

	/**
	 * @param message
	 * @param field
	 * @param parameter
	 */
	public ErrorDto(String message, String field, Object parameter) {
		this.message = message;
		this.field = field;
		this.parameter = parameter;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @return the parameter
	 */
	public Object getParameter() {
		return parameter;
	}

}
