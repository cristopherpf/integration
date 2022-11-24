package com.study.integration.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.integration.entities.Address;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class AddressServiceTest {

	@Autowired
	private AddressService service;
	
	@Test
	public void should_find_address_byZipCode() {
		Address address = service.getAddressByZipCode("84015150");
		
	}
}
