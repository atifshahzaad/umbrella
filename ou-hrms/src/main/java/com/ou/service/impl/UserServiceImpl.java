package com.ou.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ou.dto.CreateUserDTO;
import com.ou.exceptions.ResourceNotFoundException;
import com.ou.model.User;
import com.ou.repository.UserRepository;
import com.ou.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User create(CreateUserDTO dto) {
		User user = new User(dto.getId(), dto.getJoiningDate());
		if(dto.getSupervisor() != null) {
			User supervisior = this.findByIdForReference(dto.getSupervisor());
			user.setSupervisor(supervisior);
		}
		return userRepository.save(user);
	}

	@Override
	public User findByIdForReference(UUID id) {
		return userRepository.findByIdForReference(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with provided id: " + id.toString()));
	}

}
