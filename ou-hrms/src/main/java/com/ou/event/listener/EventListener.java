package com.ou.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.ou.event.UserCreatedEvent;
import com.ou.service.UserService;

@Component
public class EventListener {
	
	@Autowired
	private UserService userService;
	
	@KafkaListener(topics = "${ou_hrms.topic.userCreated}")
    public void handleNotification(UserCreatedEvent event) {
		System.out.println("#################### " + event.getId().toString() + " ############################");
		userService.create(event);
    }
}
