package com.study.integration.services.impl;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.study.integration.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	public String getEmailRegex() {
		String alphaNumericRegex = "[A-Za-z0-9_-]";
		String localRegex = "^(?=.{1,64}@)" + alphaNumericRegex + "+(\\." + alphaNumericRegex + "+)*@";
		String domainRegex = "[^-]" + alphaNumericRegex + "+(\\." + alphaNumericRegex + "+)*(\\.[A-Za-z]{2,})$";  
		
		return localRegex + domainRegex;
	}
	
	public boolean isValid(String emailAddress) {
		logger.info("E-mail validado: {}", emailAddress);
	    if(emailAddress == null)
	    	return false;
	    
		return Pattern.compile(getEmailRegex())
	                  .matcher(emailAddress)
	                  .matches();
	}
}
