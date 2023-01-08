package com.ou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ou.dto.LoginDTO;
import com.ou.dto.LoginResponse;
import com.ou.service.AuthService;

@RestController
@RequestMapping("oua/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO dto) {
		LoginResponse response = authService.login(dto);
		return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
	}
}
