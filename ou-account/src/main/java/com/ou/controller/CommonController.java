package com.ou.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ou.model.Country;
import com.ou.model.Role;
import com.ou.service.CountryService;
import com.ou.service.RoleService;
import com.ou.util.OuAccountUtil;

@RestController
@RequestMapping("oua/api/v1")
public class CommonController {

	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping("country")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Country> createCompany(@RequestBody Country country) {
		
		Country persistedCountry = countryService.create(country);
		
		return new ResponseEntity<Country>(persistedCountry, HttpStatus.CREATED);
		
	}
	
	@PostMapping("role")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Role> createRole(@RequestBody Role role, @AuthenticationPrincipal Jwt jwt) {
		
		UUID userId = OuAccountUtil.getId(jwt.getSubject());
		
		Role persistedRole = roleService.create(role);
		
		return new ResponseEntity<Role>(persistedRole, HttpStatus.CREATED);
		
	}
	
	
}
