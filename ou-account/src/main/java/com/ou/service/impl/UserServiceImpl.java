package com.ou.service.impl;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ou.dto.UserInfoDTO;
import com.ou.exceptions.InvalidOperationException;
import com.ou.exceptions.ResourceNotFoundException;
import com.ou.model.User;
import com.ou.repository.UserRepository;
import com.ou.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(UUID userId, UserInfoDTO dto) {
		
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.orElseThrow( () -> new ResourceNotFoundException("User not found for provided id: " + userId.toString()));
		
		if(user.isRegCompleted()) {
			throw new InvalidOperationException("User regestration is completed");
		}
		
		if(user.getPassword() != null && !user.getPassword().isBlank()) {
			throw new InvalidOperationException("User password is already set");
		}
		
		user.copyFromUserInfoDTO(dto);
		
		user = userRepository.save(user);
		
		user.setRegCompleted(true);
		
		return user;
	}

	@Override
	public User patch(UUID userId, Map<String, Object> data) {
		
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.orElseThrow( () -> new ResourceNotFoundException("User not found for provided id: " + userId.toString()));
		
		if(data.containsKey("password")) {
			throw new InvalidOperationException("Password update not supported by this API. Please user password update API");
		}
		
		modelMapper.map(data, user);
		
		return userRepository.save(user);
	}

}
