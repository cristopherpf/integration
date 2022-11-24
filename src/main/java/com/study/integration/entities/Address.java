package com.study.integration.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String zipCode;
	@Column(nullable = false)
	private String street;
	@Column(nullable = false)
	private Long number;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String stateCode;
	@Column(nullable = false)
	private Integer ibgeCode;
	private String complement;
	
	public Address() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getIbgeCode() {
		return ibgeCode;
	}

	public void setIbgeCode(Integer ibgeCode) {
		this.ibgeCode = ibgeCode;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, complement, ibgeCode, id, number, stateCode, street, zipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(complement, other.complement)
				&& Objects.equals(ibgeCode, other.ibgeCode) && Objects.equals(id, other.id)
				&& Objects.equals(number, other.number) && Objects.equals(stateCode, other.stateCode)
				&& Objects.equals(street, other.street) && Objects.equals(zipCode, other.zipCode);
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", zipCode=" + zipCode + ", street=" + street + ", number=" + number + ", city="
				+ city + ", stateCode=" + stateCode + ", ibgeCode=" + ibgeCode + ", complement=" + complement + "]";
	}
}
