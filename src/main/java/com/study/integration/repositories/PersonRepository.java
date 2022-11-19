package com.study.integration.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.study.integration.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	@Transactional(readOnly = true)
	Optional<Person> findByName(String name);
	
	@Transactional(readOnly = true)
	Optional<Person> findByEmail(String email);
	
	@Transactional(readOnly = true)
	Optional<Person> findByNameOrEmail(String name, String email);
}
