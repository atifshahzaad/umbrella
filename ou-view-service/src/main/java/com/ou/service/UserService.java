package com.ou.service;

import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ou.event.UserCreatedEvent;
import com.ou.event.UserNameUpdatedEvent;
import com.ou.event.UserRoleUpdatedEvent;
import com.ou.event.UserUpdatedEvent;
import com.ou.exceptions.ResourceNotFoundException;
import com.ou.model.User;
import com.ou.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	public void create(UserCreatedEvent event) {
		User user = modelMapper.map(event, User.class);
		userRepository.save(user);
	}

	public void updateName(UserNameUpdatedEvent event) {

		System.out.println(event.toString());

		User user = userRepository.findByUserId(event.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with user id:" + event.getUserId()));

		user.setFirstName(event.getFirstName());
		user.setMiddleName(event.getMiddleName());
		user.setLastName(event.getLastName());
		user.setUpdatedAt(event.getUpdatedAt());

		userRepository.save(user);
	}

	public void updateUser(UserUpdatedEvent event) {

		System.out.println("Event");
		System.out.println(event.toString());

		System.out.println();

		User user = userRepository.findByUserId(event.getUserId().toString())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with user id:" + event.getUserId()));
		modelMapper.map(event, user);

		System.out.println("User");

		System.out.println(user.toString());

		userRepository.save(user);
	}

	public void updateRoles(UserRoleUpdatedEvent event) {

		User user = userRepository.findByUserId(event.getUserId().toString())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with user id:" + event.getUserId()));
		user.setRoles(event.getRoles());
		userRepository.save(user);
	}

}
