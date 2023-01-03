package com.ou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ou.dto.CreateUserDTO;
import com.ou.model.User;
import com.ou.service.UserService;

@RestController
@RequestMapping("ouh/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody CreateUserDTO dto) {
		User user = userService.create(dto);
		return ResponseEntity.created(null).build();
	}

}
