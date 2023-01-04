package com.ou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ou.event.UserCreatedEvent;
import com.ou.service.HRMSEventService;

@Service
public class HRMSEventServiceImpl implements HRMSEventService {

	@Value("${ou_account.topic.userCreated}")
	private String userCreatedTopic;
	
	@Autowired
	private KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;
	
	@Override
	public void sendUserCreatedEvent(UserCreatedEvent event) {
		kafkaTemplate.send(userCreatedTopic, event);

	}

}
