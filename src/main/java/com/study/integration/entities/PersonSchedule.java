package com.study.integration.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PersonSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	public PersonSchedule() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
