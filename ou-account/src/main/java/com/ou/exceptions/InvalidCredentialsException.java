package com.ou.exceptions;

import com.ou.dto.LoginErrorDTO;

public class InvalidCredentialsException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidCredentialsException(LoginErrorDTO error) {
		super(error.getError_description());
	}
	
}
