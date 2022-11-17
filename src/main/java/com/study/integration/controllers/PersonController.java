package com.study.integration.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.integration.controllers.exceptions.PersonNotFoundException;
import com.study.integration.entities.Person;
import com.study.integration.services.PersonService;

@RestController
@RequestMapping(path = "/person")
public class PersonController {

	@Autowired
	private PersonService service;
	
	@PostMapping
	public ResponseEntity<Person> insert(@RequestBody Person person) throws Exception {
		return new ResponseEntity<Person>(service.save(person), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Person>> findAll() {
		return new ResponseEntity<List<Person>>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Person> findById(@PathVariable Long id) {
		Optional<Person> person = service.findById(id);
		if(person.isPresent())
			return new ResponseEntity<Person>(person.get(), HttpStatus.OK);
		else
		    throw new PersonNotFoundException(id);
	}
	
	@GetMapping("/name")
	public ResponseEntity<Person> findByName(@RequestParam String name) {
		Optional<Person> person = service.findByName(name);
		if(person.isPresent())
			return new ResponseEntity<Person>(person.get(), HttpStatus.OK);
		else
			throw new PersonNotFoundException(name);
	}
}
