package com.study.integration.utils;

import java.util.concurrent.TimeUnit;

public class DateUtils {

	private DateUtils() {}
	
	public static Long convertHourToMilisecond(Integer hour) {
		return TimeUnit.HOURS.toMillis(hour);
	}
	
	public static String formatMilisecondToTime(Long milisecond) {
		long hours = TimeUnit.MILLISECONDS.toHours(milisecond);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(milisecond) % 60;
		
		return String.format("%02d:%02d", hours, minutes);
	}
}
