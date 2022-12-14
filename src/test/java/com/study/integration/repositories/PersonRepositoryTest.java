package com.study.integration.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.integration.entities.Person;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class PersonRepositoryTest {

	private static final String PERSON_NAME = "Person Test";
	private static final String PERSON_EMAIL = "person@test.com";
	
	@Autowired
	private PersonRepository repository;
	
	@BeforeAll
	void setUp() {
		Person person = new Person(PERSON_NAME, PERSON_EMAIL);
		repository.save(person);
	}
	
	@AfterAll
	void tearDown() {
		repository.deleteAll();
	}
	
	@Test
	void should_find_byName() {
		Optional<Person> person = repository.findByName(PERSON_NAME);
		assertEquals(PERSON_NAME, person.get().getName());
		assertEquals(PERSON_EMAIL, person.get().getEmail());
	}
	
	@Test
	void should_not_find_byName_when_invalid() {
		Optional<Person> person = repository.findByName("Invalid name");
		assertThat(!person.isPresent());
	}
	
	@Test
	void should_find_byEmail() {
		Optional<Person> person = repository.findByEmail(PERSON_EMAIL);
		assertEquals(PERSON_NAME, person.get().getName());
		assertEquals(PERSON_EMAIL, person.get().getEmail());
	}
	
	@Test
	void should_not_find_byEmail_when_invalid() {
		Optional<Person> person = repository.findByName("invalid@email.com");
		assertThat(!person.isPresent());
	}
	
	@Test
	void should_find_byNameOrEmail() {
		Optional<Person> person = repository.findByNameOrEmail(PERSON_NAME, PERSON_EMAIL);
		assertEquals(PERSON_NAME, person.get().getName());
		assertEquals(PERSON_EMAIL, person.get().getEmail());
	}
	
	@Test
	void should_not_find_byNameOrEmail_when_invalid() {
		Optional<Person> person = repository.findByNameOrEmail("Invalid name", "invalid@email.com");
		assertThat(!person.isPresent());
	}
	
	@Test
	void should_find_byNameOrEmail_when_invalidEmail() {
		Optional<Person> person = repository.findByNameOrEmail(PERSON_NAME, "invalid@email.com");
		assertEquals(PERSON_NAME, person.get().getName());
		assertEquals(PERSON_EMAIL, person.get().getEmail());
	}
	
	@Test
	void should_find_byNameOrEmail_when_invalidName() {
		Optional<Person> person = repository.findByNameOrEmail("Invalid name", PERSON_EMAIL);
		assertEquals(PERSON_NAME, person.get().getName());
		assertEquals(PERSON_EMAIL, person.get().getEmail());
	}
}
