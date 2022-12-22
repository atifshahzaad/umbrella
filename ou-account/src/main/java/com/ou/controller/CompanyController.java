package com.ou.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ou.dto.CreateCompanyDTO;
import com.ou.model.Company;
import com.ou.service.CompanyService;

@RestController
@RequestMapping("oua/api/v1/company")
public class CompanyController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private CompanyService companyService;

	@PostMapping
	public ResponseEntity<Company> create(@RequestBody CreateCompanyDTO dto) {
		
		LOGGER.info("starting company creation");
		
		Company company = companyService.create(dto);
		
		LOGGER.info("company is created send the response");
		
		return new ResponseEntity<Company>(company, HttpStatus.CREATED);
	}
}
