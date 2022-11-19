package com.study.integration.services;

import java.util.List;
import java.util.Optional;

import com.study.integration.entities.Person;

public interface PersonService {

	Optional<Person> findById(Long id);
	
	Optional<Person> findByName(String name);
	
	Optional<Person> findByEmail(String email);
	
	List<Person> findAll();
	
	Person save(Person person);
	
	void delete(Person person);
}
