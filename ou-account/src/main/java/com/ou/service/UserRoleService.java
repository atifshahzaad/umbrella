package com.ou.service;

import java.util.List;
import java.util.UUID;

import com.ou.dto.UserSearchResultDTO;
import com.ou.model.UserRole;

public interface UserRoleService {

	UserRole create(UserRole userRole);

	void delete(UUID id);
	
	List<UserSearchResultDTO> searchUserByName(String name);

}
