package com.study.integration.exceptions;

public class DefaultScheduleAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DefaultScheduleAlreadyExistsException() {
        super("Disponibilidade de agenda já esta criada.");
	}
}
