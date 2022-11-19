package com.study.integration.utils;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailUtils {
	
	private EmailUtils() {}
	
	private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);
	
	final static String REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	
	public static boolean isValid(String emailAddress) {
		logger.info("E-mail validado: {}", emailAddress);
	    if(emailAddress == null)
	    	return false;
	    
		return Pattern.compile(REGEX_PATTERN)
	                  .matcher(emailAddress)
	                  .matches();
	}
}
