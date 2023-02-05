package com.ou.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ou.dto.CreateUserDTO;
import com.ou.dto.UpdateUserDTO;
import com.ou.model.User;
import com.ou.service.UserService;
import com.ou.util.OuHrmsUtil;

@RestController
@RequestMapping("ouh/api/v1/user")
public class UserController {

	@Autowired
	private SmartValidator validator;
	
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody CreateUserDTO dto) {
		userService.create(dto);
		return ResponseEntity.created(null).build();
	}
	
	@PatchMapping
	public ResponseEntity<User> patch(@RequestBody Map<String, Object> data, @AuthenticationPrincipal Jwt jwt)
			throws MethodArgumentNotValidException {

		UUID userId = OuHrmsUtil.getId(jwt.getSubject());
		
		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(data, "data");

		for (Entry<String, Object> entry : data.entrySet()) {
			String key = entry.getKey();
			Object d = entry.getValue();
			validator.validateValue(UpdateUserDTO.class, key, d, errors);
		}

		if (errors.hasErrors()) {
			throw new MethodArgumentNotValidException(null, errors);
		}
		
		User user = userService.patch(userId, data);
		return ResponseEntity.ok(user);
	}

}
