package com.study.integration.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.study.integration.entities.Person;
import com.study.integration.exceptions.EmailNotValidException;
import com.study.integration.repositories.PersonRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class PersonServiceTest {

	@Autowired
    private PersonService service;

	@MockBean
    private PersonRepository repository;
    
    @BeforeAll
	public void setUp() {
    	BDDMockito.given(repository.findById(Mockito.anyLong())).willReturn(Optional.of(new Person()));
    	BDDMockito.given(repository.findByName(Mockito.anyString())).willReturn(Optional.of(new Person()));
    	BDDMockito.given(repository.findByEmail(Mockito.anyString())).willReturn(Optional.of(new Person()));
    	BDDMockito.given(repository.findAll()).willReturn(new ArrayList<Person>());
    	BDDMockito.given(repository.save(Mockito.any(Person.class))).willReturn(new Person());
	}
    
    @Test
    void should_find_byId() {
        Optional<Person> person = service.findById(1L);
        assertThat(person.isPresent());
    }
    
    @Test
    void should_find_byName() {
    	Optional<Person> person = service.findByName("Person Test");
    	assertThat(person.isPresent());
    }
    
    @Test
    void should_find_byEmail() {
    	Optional<Person> person = service.findByEmail("person@test.com");
    	assertThat(person.isPresent());
    }

    @Test
    void should_find_all() {
    	List<Person> persons = service.findAll();
        assertNotNull(persons);
    }
    
    /*@Test
    void should_save() {
        Person person = service.save(new Person("Person Test", "person@test.com"));
        assertNotNull(person);
    }*/
    
    @Test
    void should_not_save_when_invalidEmail() {
    	assertThrows(EmailNotValidException.class,
    	           	 () -> {service.save(new Person("Person Test", "invalidemail"));}
    	);
    }
}
