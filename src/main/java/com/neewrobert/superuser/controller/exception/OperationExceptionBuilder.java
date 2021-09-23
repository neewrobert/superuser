package com.neewrobert.superuser.controller.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class OperationExceptionBuilder {

	@Autowired
	Environment env;

	public OperationException build(String properties) {

		return new OperationException(env.getProperty(properties));

	}

}
