package com.study.integration.controllers.exceptions;

public class EmailNotValidException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmailNotValidException(String email) {
		super("E-mail: " + email + " não é válido.");
	}
}
