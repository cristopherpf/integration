package com.study.integration.services;

import java.time.DayOfWeek;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.integration.entities.AvailableSchedule;
import com.study.integration.entities.Person;
import com.study.integration.repositories.PersonRepository;
import com.study.integration.utils.DateUtils;
import com.study.integration.utils.EmailUtils;

@Service
public class PersonService {

	public static Integer DEFAULT_INITIAL_HOUR = 8;
	public static Integer DEFAULT_FINAL_HOUR = 18;
	
	@Autowired
	private PersonRepository repository;
	
	public Person save(Person person) throws Exception {
		if(!EmailUtils.isValid(person.getEmail()))
			throw new Exception("E-mail: " + person.getEmail() + " não é válido.");
		
		return repository.save(person);
	}

	public void delete(Person person) {
		repository.delete(person);
	}
	
	public List<Person> findAll() {
		return repository.findAll();
	}
	
	public Optional<Person> findById(Long id) {
		return repository.findById(id);
	}
	
	public Optional<Person> findByName(String name) {
		return repository.findByName(name);
	}
	
	public List<AvailableSchedule> createDefaultAvailableSchedule(Long personId) throws Exception {
		Optional<Person> person = repository.findById(personId);
		if(!person.isPresent())
			throw new Exception("Não existe pessoa com o ID: " + personId);
		return createDefaultAvailableSchedule(person.get());
	}
	
	public List<AvailableSchedule> createDefaultAvailableSchedule(Person person) {
		for(DayOfWeek day : EnumSet.allOf(DayOfWeek.class))
		    person.addSchedule(new AvailableSchedule(person, 
		    										 day, 
		    										 DateUtils.convertHourToMilisecond(DEFAULT_INITIAL_HOUR), 
		    										 DateUtils.convertHourToMilisecond(DEFAULT_FINAL_HOUR)));

		return repository.save(person).getAvailableSchedules();
	}
}
