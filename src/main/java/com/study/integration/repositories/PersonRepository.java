package com.study.integration.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.integration.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findByName(String name);
}
