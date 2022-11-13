package com.study.integration.utils;

import java.util.regex.Pattern;

public class EmailUtils {
	
	final static String REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	
	public static boolean isValid(String emailAddress) {
	    return Pattern.compile(REGEX_PATTERN)
	                  .matcher(emailAddress)
	                  .matches();
	}
}
