package com.neewrobert.superuser.controller.response;

import java.util.List;

import com.neewrobert.superuser.dto.ErrorDto;

public class ErrorResponse {

	private final String message;
	private final int code;
	private final String status;
	private final String objectName;
	private final List<ErrorDto> errors;

	/**
	 * @param message
	 * @param code
	 * @param status
	 * @param objectName
	 * @param errors
	 */
	public ErrorResponse(String message, int code, String status, String objectName, List<ErrorDto> errors) {
		this.message = message;
		this.code = code;
		this.status = status;
		this.objectName = objectName;
		this.errors = errors;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the objectName
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * @return the errors
	 */
	public List<ErrorDto> getErrors() {
		return errors;
	}

}
