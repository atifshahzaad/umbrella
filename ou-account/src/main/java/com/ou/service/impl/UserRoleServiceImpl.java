package com.ou.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ou.model.UserRole;
import com.ou.repository.UserRoleRepository;
import com.ou.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleServiceImpl.class);
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public UserRole create(UserRole userRole) {
		
		LOGGER.info("assigning role: " + userRole.getRole().getName() + " to user with id: " + userRole.getUser().getId());
		
		return userRoleRepository.save(userRole);
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub

	}

}
