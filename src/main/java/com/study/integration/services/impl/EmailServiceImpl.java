package com.study.integration.services.impl;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.study.integration.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	private static final String REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	
	public boolean isValid(String emailAddress) {
		logger.info("E-mail validado: {}", emailAddress);
	    if(emailAddress == null)
	    	return false;
	    
		return Pattern.compile(REGEX_PATTERN)
	                  .matcher(emailAddress)
	                  .matches();
	}
}
