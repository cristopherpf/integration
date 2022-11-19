package com.study.integration.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.integration.entities.Person;
import com.study.integration.exceptions.EmailNotValidException;
import com.study.integration.repositories.PersonRepository;
import com.study.integration.services.PersonService;
import com.study.integration.utils.EmailUtils;

@Service
public class PersonServiceImpl implements PersonService {

	private Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
	
	@Autowired
	private PersonRepository repository;

	@Override
	public Optional<Person> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Person> findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Optional<Person> findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public List<Person> findAll() {
		return repository.findAll();
	}

	@Override
	public Person save(Person person) {
		logger.info("Salvando pessoa {}", person);
		if(!EmailUtils.isValid(person.getEmail()))
			throw new EmailNotValidException(person.getEmail());
		
		return repository.save(person);
	}

	@Override
	public void delete(Person person) {
		logger.info("Excluindo pessoa {}", person);
		repository.delete(person);
	}
}
