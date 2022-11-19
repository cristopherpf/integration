package com.study.integration.services;

import java.util.List;

import com.study.integration.entities.AvailableSchedule;

public interface ScheduleService {
	
	List<AvailableSchedule> saveDefaultAvailableSchedule(Long personId);
	
	List<AvailableSchedule> findByPersonId(Long personId);
}
