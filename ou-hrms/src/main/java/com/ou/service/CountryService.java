package com.ou.service;

import java.util.List;
import java.util.UUID;

import com.ou.model.Country;

public interface CountryService {

	Country create(Country country);

	Country findByIdForReference(UUID id);

	List<Country> findAll();
}
