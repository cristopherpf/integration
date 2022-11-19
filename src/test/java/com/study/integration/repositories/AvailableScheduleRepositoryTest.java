package com.study.integration.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.integration.entities.AvailableSchedule;
import com.study.integration.entities.Person;
import com.study.integration.utils.DateUtils;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class AvailableScheduleRepositoryTest {

	private static final String PERSON_NAME = "Person Test";
	private static final String PERSON_EMAIL = "person@test.com";
	private Long personId;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AvailableScheduleRepository availableScheduleRepository;
	
	@BeforeAll
	void setUp() {
		Person person = new Person(PERSON_NAME, PERSON_EMAIL);
		person = personRepository.save(person);
		this.personId = person.getId();
		
		availableScheduleRepository.save(new AvailableSchedule(person, 
															   DayOfWeek.SATURDAY, 
														       DateUtils.convertHourToMilisecond(8), 
														       DateUtils.convertHourToMilisecond(18)));
		
		availableScheduleRepository.save(new AvailableSchedule(person, 
															   DayOfWeek.FRIDAY, 
														       DateUtils.convertHourToMilisecond(8), 
														       DateUtils.convertHourToMilisecond(18)));
	}
	
	@AfterAll
	void tearDown() {
		availableScheduleRepository.deleteAll();
		personRepository.deleteAll();
	}
	
	@Test
	void should_find_byPersonId() {
		List<AvailableSchedule> schedules = availableScheduleRepository.findByPersonId(personId);
		assertEquals(2, schedules.size());
	}
	
	@Test
	void should_not_find_byPersonId() {
		List<AvailableSchedule> schedules = availableScheduleRepository.findByPersonId(-1L);
		assertThat(schedules.isEmpty());
	}
}
