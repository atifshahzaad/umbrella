package com.ou.service;

import java.util.Map;
import java.util.UUID;

import com.ou.dto.CreateUserDTO;
import com.ou.event.UserCreatedEvent;
import com.ou.model.User;

public interface UserService {

	User create(CreateUserDTO dto);
	
	User update(UUID userId, Map<String, Object> map);
	
	User patch(UUID userId, Map<String, Object> data);
	
	void create(UserCreatedEvent event);
	
	User findByIdForReference(UUID id);
	
	User findById(UUID id);
	
}
