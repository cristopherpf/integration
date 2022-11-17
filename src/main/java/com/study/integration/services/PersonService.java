package com.study.integration.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.integration.controllers.exceptions.EmailNotValidException;
import com.study.integration.entities.Person;
import com.study.integration.repositories.PersonRepository;
import com.study.integration.utils.EmailUtils;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	public Person save(Person person) throws EmailNotValidException {
		if(!EmailUtils.isValid(person.getEmail()))
			throw new EmailNotValidException(person.getEmail());
		
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
}
