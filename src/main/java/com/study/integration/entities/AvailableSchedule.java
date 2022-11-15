package com.study.integration.entities;

import java.time.DayOfWeek;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.integration.utils.DateUtils;

@Entity
public class AvailableSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonIgnore
	private Person person;
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private DayOfWeek dayOfWeek;
	@Column(nullable = false)
	@JsonIgnore
	private Long initialHour;
	@Column(nullable = false)
	@JsonIgnore
	private Long finalHour;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient
	private String initialDate;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient
	private String finalDate;
	
	public AvailableSchedule() {}

	public AvailableSchedule(Person person, DayOfWeek dayOfWeek, Long initialHour, Long finalHour) {
		super();
		this.person = person;
		this.dayOfWeek = dayOfWeek;
		this.initialHour = initialHour;
		this.finalHour = finalHour;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Long getInitialHour() {
		return initialHour;
	}

	public void setInitialHour(Long initialHour) {
		this.initialHour = initialHour;
	}

	public Long getFinalHour() {
		return finalHour;
	}

	public void setFinalHour(Long finalHour) {
		this.finalHour = finalHour;
	}

	public String getInitialDate() {
		return DateUtils.formatMilisecondToTime(initialHour);
	}
	
	public String getFinalDate() {
		return DateUtils.formatMilisecondToTime(finalHour);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dayOfWeek, finalHour, id, initialHour, person);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvailableSchedule other = (AvailableSchedule) obj;
		return dayOfWeek == other.dayOfWeek && Objects.equals(finalHour, other.finalHour)
				&& Objects.equals(id, other.id) && Objects.equals(initialHour, other.initialHour)
				&& Objects.equals(person, other.person);
	}

	@Override
	public String toString() {
		return "AvailableSchedule [person=" + person + ", dayOfWeek=" + dayOfWeek.name() + ", initialHour=" + initialHour
				+ ", finalHour=" + finalHour + "]";
	}
}
