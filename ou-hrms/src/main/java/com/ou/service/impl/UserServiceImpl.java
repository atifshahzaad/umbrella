package com.ou.service.impl;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ou.dto.CreateUserDTO;
import com.ou.event.UserCreatedEvent;
import com.ou.event.UserUpdatedEvent;
import com.ou.event.producer.EventProducer;
import com.ou.exceptions.ResourceNotFoundException;
import com.ou.model.User;
import com.ou.repository.UserRepository;
import com.ou.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Value("${ou-hrms.topic.userUpdated}")
	private String userUpdatedTopic;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EventProducer eventProducer;

	@Override
	public User create(CreateUserDTO dto) {
		User user = new User(dto.getId(), dto.getJoiningDate());
		if (dto.getSupervisor() != null) {
			User supervisior = this.findByIdForReference(dto.getSupervisor());
			user.setSupervisor(supervisior);
		}
		return userRepository.save(user);
	}

	@Override
	public User update(UUID userId, Map<String, Object> map) {
		User user = this.findByIdForReference(userId);

		return null;
	}

	@Override
	public User findByIdForReference(UUID id) {
		return userRepository.findByIdForReference(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with provided id: " + id.toString()));
	}

	@Override
	public void create(UserCreatedEvent event) {
		User user = new User(event.getUserId(), event.getEmail(), event.getJoiningDate());
		if (event.getSupervisor() != null) {
			User supervisior = this.findByIdForReference(event.getSupervisor());
			user.setSupervisor(supervisior);
		}
		userRepository.save(user);
	}

	@Override
	public User findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User patch(UUID userId, Map<String, Object> data) {
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.orElseThrow(
				() -> new ResourceNotFoundException("User not found for provided id: " + userId.toString()));
		
		
		modelMapper.map(data, user);
		User persistedUser = userRepository.save(user);
		UserUpdatedEvent event = modelMapper.map(persistedUser, UserUpdatedEvent.class);
		event.setUserId(persistedUser.getId());
		eventProducer.send(userUpdatedTopic, event);
		
		return persistedUser;
	}

}
