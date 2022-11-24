package com.study.integration.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.study.integration.dtos.AddressDTO;
import com.study.integration.entities.Address;
import com.study.integration.services.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	private Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String URI = "http://viacep.com.br/ws/{zip_code}/json/";
	
	@Override
	public Address getAddressByZipCode(String zipCode) {
		logger.info("Consultando cep {}", zipCode);
		final Map<String, String> variables = new HashMap<String, String>();
		variables.put("zip_code", zipCode);
		
		AddressDTO addressDTO = restTemplate.getForObject(URI, AddressDTO.class, variables);
		logger.info("Endereço retornado {}", addressDTO);
		
		//TODO: Utilizar modelMapper para criar a entidade Address
		return new Address();
	}	
}
