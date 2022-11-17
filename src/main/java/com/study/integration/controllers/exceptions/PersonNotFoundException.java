package com.study.integration.controllers.exceptions;

public class PersonNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PersonNotFoundException() {
        super("Pessoa não cadastrada.");
	}
	
	public PersonNotFoundException(String personName) {
        super("Pessoa com o nome " + personName + " não cadastrada.");
	}
	
	public PersonNotFoundException(Long personId) {
        super("Pessoa com o ID " + personId + " não cadastrada.");
	}
}
