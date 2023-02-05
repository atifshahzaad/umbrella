package com.ou.service.impl;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ou.dto.UserInfoDTO;
import com.ou.event.UserNameUpdatedEvent;
import com.ou.event.producer.EventProducer;
import com.ou.exceptions.InvalidOperationException;
import com.ou.exceptions.ResourceNotFoundException;
import com.ou.model.User;
import com.ou.repository.UserRepository;
import com.ou.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Value("${ou_account.topic.userNameUpdated}")
	private String userNameUpdatedTopic;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EventProducer eventProducer;

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(UUID userId, UserInfoDTO dto) {

		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.orElseThrow(
				() -> new ResourceNotFoundException("User not found for provided id: " + userId.toString()));

		if (user.isRegCompleted()) {
			throw new InvalidOperationException("User regestration is completed");
		}

		if (user.getPassword() != null && !user.getPassword().isBlank()) {
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
		User user = optionalUser.orElseThrow(
				() -> new ResourceNotFoundException("User not found for provided id: " + userId.toString()));

		if (data.containsKey("password")) {
			throw new InvalidOperationException(
					"Password update not supported by this API. Please user password update API");
		}

		modelMapper.map(data, user);
		user.setRegCompleted(true);

		User persistedUser = userRepository.save(user);

		UserNameUpdatedEvent event = UserNameUpdatedEvent.builder()
				.userId(persistedUser.getId())
				.firstName(persistedUser.getFirstName())
				.middleName(persistedUser.getMiddleName())
				.lastName(persistedUser.getLastName())
				.updatedAt(persistedUser.getUpdatedAt()).build();

		eventProducer.send(userNameUpdatedTopic, event);
		
		return persistedUser;
	}

}
