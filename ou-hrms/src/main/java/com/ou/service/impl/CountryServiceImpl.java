package com.ou.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.ou.exceptions.ResourceNotFoundException;
import com.ou.model.Country;
import com.ou.repository.CountryRepository;
import com.ou.service.CountryService;

public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public Country create(Country country) {
		return countryRepository.save(country);
	}

	@Override
	public Country findByIdForReference(UUID id) {
		return countryRepository.findByIdForReference(id).orElseThrow( () -> new ResourceNotFoundException("Country not found with provided id: " + id.toString()) );
	}

	@Override
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

}
