package com.study.integration.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.integration.dtos.GenericDTO;
import com.study.integration.entities.AvailableSchedule;
import com.study.integration.services.ScheduleService;

@RestController
@RequestMapping(path = "/schedule")
public class ScheduleController {
	
	@Autowired
	private ScheduleService service;
	
	@PostMapping(value = "/createdefault")
	public ResponseEntity<List<AvailableSchedule>> createDefaultAvailableSchedule(@RequestBody GenericDTO person) throws Exception {
		return new ResponseEntity<List<AvailableSchedule>>(service.saveDefaultAvailableSchedule(person.getId()), HttpStatus.CREATED);
	}
}
	