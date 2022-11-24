package com.study.integration.services;

import com.study.integration.entities.Address;

public interface AddressService {
	
	Address getAddressByZipCode(String zipCode);
	
}
