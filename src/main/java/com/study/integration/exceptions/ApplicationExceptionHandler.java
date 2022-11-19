package com.study.integration.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleException(Exception e) {
		return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Erro: " + e.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handleConstraintException(Exception e) {
		return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Erro: Valor deve ser unico."), HttpStatus.BAD_REQUEST);
	}
}
