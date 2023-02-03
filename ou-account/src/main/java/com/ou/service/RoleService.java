package com.ou.service;

import java.util.List;
import java.util.UUID;

import com.ou.model.Role;

public interface RoleService {

	Role create(Role role);
	
	Role findByIdForReference(UUID id);
	
	Role findByNameForReference(String name);
	
	List<Role> findAll();
	
	void delete(UUID id);
}
