package com.study.integration.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDTO {

	@JsonProperty("cep")
	private String zipCode;
	@JsonProperty("logradouro")
	private String street;
	@JsonProperty("localidade")
	private String city;
	@JsonProperty("uf")
	private String stateCode;
	@JsonProperty("ibge")
	private Integer ibgeCode;
	
	public AddressDTO() {}

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

	@Override
	public String toString() {
		return "AddressDTO [zipCode=" + zipCode + ", street=" + street + ", city=" + city + ", stateCode=" + stateCode
				+ ", ibgeCode=" + ibgeCode + "]";
	}
}
