package com.ou.service;

import java.util.UUID;

import com.ou.dto.CreateUserDTO;
import com.ou.model.User;

public interface UserService {

	User create(CreateUserDTO dto);
	
	User findByIdForReference(UUID id);
}
