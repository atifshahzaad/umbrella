package com.ou.controller;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ou.dto.UserDetailDTO;
import com.ou.dto.UserInfoDTO;
import com.ou.dto.UserSearchResultDTO;
import com.ou.model.User;
import com.ou.service.UserDetailService;
import com.ou.service.UserRoleService;
import com.ou.service.UserService;
import com.ou.util.OuAccountUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("oua/api/v1/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private SmartValidator validator;

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailService userDetailService;

	@Autowired
	private UserRoleService userRoleService;

	@GetMapping("/my/detail")
	public ResponseEntity<UserDetailDTO> get(@AuthenticationPrincipal Jwt jwt) {
		UUID userId = OuAccountUtil.getId(jwt.getSubject());
		UserDetailDTO dto = userDetailService.get(userId);
		return new ResponseEntity<UserDetailDTO>(dto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDetailDTO> get(@PathVariable("id") UUID id) {
		UserDetailDTO dto = userDetailService.get(id);
		return new ResponseEntity<UserDetailDTO>(dto, HttpStatus.OK);
	}

	@GetMapping("/search/{key}")
	public ResponseEntity<List<UserSearchResultDTO>> searchUserByName(@PathVariable("key") String key) {
		return new ResponseEntity<List<UserSearchResultDTO>>(userRoleService.searchUserByName(key), HttpStatus.OK);
	}

	@PatchMapping("/{id}/password")
	public void updatePassword() {

	}

	@PutMapping("/{id}")
	// @Secured("ROLE_ADMIN")
	public ResponseEntity<User> update(@PathVariable("id") UUID id, @Valid @RequestBody UserInfoDTO dto) {

		LOGGER.info(MessageFormat.format("Updating user with id: {0}", id.toString()));

		// todo: companyId will be used for checking company status and billing

		User user = userService.update(id, dto);
		return ResponseEntity.ok(user);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<User> patch(@PathVariable("id") UUID id, @RequestBody Map<String, Object> data)
			throws MethodArgumentNotValidException {

		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(data, "data");

		for (Entry<String, Object> entry : data.entrySet()) {
			String key = entry.getKey();
			Object d = entry.getValue();
			validator.validateValue(UserInfoDTO.class, key, d, errors);
		}

		if (errors.hasErrors()) {
			throw new MethodArgumentNotValidException(null, errors);
		}

		User user = userService.patch(id, data);
		return ResponseEntity.ok(user);

	}
}
