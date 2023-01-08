package com.ou.controller;

import java.text.MessageFormat;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ou.dto.UserInfoDTO;
import com.ou.model.User;
import com.ou.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("oua/api/v1/company/{companyId}/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	

	@PutMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<User> update(@PathVariable("companyId") UUID companyId, @PathVariable("id") UUID id,
			@Valid @RequestBody UserInfoDTO dto) {
		

		LOGGER.info(MessageFormat.format("Updating user for company id: {0}, user id: {1}", companyId.toString()), id.toString());

		//todo: companyId will be used for checking company status and billing
		
		User user = userService.update(id, dto);
		return ResponseEntity.ok(user);
	}
}
