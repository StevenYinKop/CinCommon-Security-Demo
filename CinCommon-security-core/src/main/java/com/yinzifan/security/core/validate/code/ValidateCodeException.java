package com.yinzifan.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6575054590209179628L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
