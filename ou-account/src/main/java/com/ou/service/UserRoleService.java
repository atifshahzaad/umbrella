package com.ou.service;

import java.util.UUID;

import com.ou.model.UserRole;

public interface UserRoleService {

	UserRole create(UserRole userRole);

	void delete(UUID id);

}
