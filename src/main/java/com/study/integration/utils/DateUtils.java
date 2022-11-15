package com.study.integration.utils;

import java.util.concurrent.TimeUnit;

public class DateUtils {

	//TODO: fazer teste unitário
	public static Long convertHourToMilisecond(Integer hour) {
		return TimeUnit.HOURS.toMillis(hour);
	}
	
	//TODO: fazer teste unitário
	public static String formatMilisecondToTime(Long milisecond) {
		long HH = TimeUnit.MILLISECONDS.toHours(milisecond);
		long MM = TimeUnit.MILLISECONDS.toMinutes(milisecond) % 60;
		
		String formattedTime = String.format("%02d:%02d", HH, MM);
		return formattedTime;
	}
}
