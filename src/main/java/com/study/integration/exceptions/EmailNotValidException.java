package com.study.integration.exceptions;

public class EmailNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailNotValidException(String email) {
		super("E-mail: " + email + " não é válido.");
	}
}
