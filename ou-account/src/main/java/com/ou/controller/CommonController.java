package com.ou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ou.model.Country;
import com.ou.service.CountryService;

@RestController
@RequestMapping("oua/api/v1")
public class CommonController {

	
	@Autowired
	private CountryService countryService;
	
	@PostMapping("country")
	public ResponseEntity<Country> createCompany(@RequestBody Country country) {
		
		Country persistedCountry = countryService.create(country);
		
		return new ResponseEntity<Country>(persistedCountry, HttpStatus.CREATED);
		
	}
}
