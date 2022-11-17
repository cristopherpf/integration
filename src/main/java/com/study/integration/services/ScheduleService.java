package com.study.integration.services;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.integration.controllers.exceptions.DefaultScheduleAlreadyExistsException;
import com.study.integration.controllers.exceptions.PersonNotFoundException;
import com.study.integration.entities.AvailableSchedule;
import com.study.integration.entities.Person;
import com.study.integration.repositories.AvailableScheduleRepository;
import com.study.integration.repositories.PersonRepository;
import com.study.integration.utils.DateUtils;

@Service
public class ScheduleService {
	
	public static final Integer DEFAULT_INITIAL_HOUR = 8;
	public static final Integer DEFAULT_FINAL_HOUR = 18;
	
	@Autowired
	private AvailableScheduleRepository repository;
	
	@Autowired
	private PersonRepository personRepository;
	
	public List<AvailableSchedule> saveDefaultAvailableSchedule(Long personId) {
		Optional<Person> person = personRepository.findById(personId);
		validateDefaultAvailableSchedule(person);
		
		List<AvailableSchedule> availableSchedules = createDefaultAvailableSchedules(person.get());
		for (AvailableSchedule availableSchedule : availableSchedules)
			repository.save(availableSchedule);
		
		return availableSchedules;
	}
	
	private void validateDefaultAvailableSchedule(Optional<Person> person) {
		if(!person.isPresent())
			throw new PersonNotFoundException();
		
		if(!canCreateDefaultAvailableSchedule(person.get()))
			throw new DefaultScheduleAlreadyExistsException();
	}
	
	private Boolean canCreateDefaultAvailableSchedule(Person person) {
		return person.getAvailableSchedules().isEmpty();
	}
	
	private List<AvailableSchedule> createDefaultAvailableSchedules(Person person) {
		List<AvailableSchedule> availableSchedules = new ArrayList<AvailableSchedule>();
		for(DayOfWeek day : EnumSet.allOf(DayOfWeek.class))
			availableSchedules.add(new AvailableSchedule(person, 
			    										 day, 
			    										 DateUtils.convertHourToMilisecond(DEFAULT_INITIAL_HOUR), 
			    										 DateUtils.convertHourToMilisecond(DEFAULT_FINAL_HOUR)));

		return availableSchedules;
	}
}
