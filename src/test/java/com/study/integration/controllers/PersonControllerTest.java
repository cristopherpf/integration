package com.study.integration.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.integration.entities.Person;
import com.study.integration.services.PersonService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestPropertySource(locations={"classpath:application.properties"})
class PersonControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PersonService service;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private Person createPerson() {
		return new Person("Person tst", "person@tst.com");
	}
	
	@Test
	void should_create_person() throws Exception {
		Person person = createPerson();
		
		String json = mapper.writeValueAsString(person);
		
		when(service.save(any(Person.class))).thenReturn(person);
		
		mvc.perform(
		    MockMvcRequestBuilders.post("/person")
			.contentType(MediaType.APPLICATION_JSON)
            .content(json)
        ).andExpect(status().isCreated());
		
		ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);
		verify(service).save(captor.capture());
		
		assertEquals(person.getName(), captor.getValue().getName());
	}
	
	@Test
	void should_find_person_byId() throws Exception {
		Long id = 1L;
		Person person = createPerson();
		person.setId(id);
		
		when(service.findById(eq(id))).thenReturn(Optional.of(person));
		
		mvc.perform(
		    MockMvcRequestBuilders.get("/person/" + id)
			.accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
		 .andExpect(jsonPath("$.name", Matchers.is(person.getName())));
	}
	
	@Test
	void should_find_all_persons() throws Exception {
		List<Person> persons = new ArrayList<>();
		persons.add(createPerson());
		
		when(service.findAll()).thenReturn(persons);
		
		mvc.perform(
		    MockMvcRequestBuilders.get("/person")
			.accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
		.andExpect(jsonPath("$.[0].name", Matchers.is(persons.get(0).getName())));
	}
	
}
