package com.study.integration.controllers.exceptions;

public class DefaultScheduleAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DefaultScheduleAlreadyExistsException() {
        super("Disponibilidade de agenda já esta criada.");
	}
}
