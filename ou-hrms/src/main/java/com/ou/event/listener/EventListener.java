package com.ou.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ou.event.UserCreatedEvent;
import com.ou.service.UserService;

@Service
public class EventListener {
	
	@Autowired
	private UserService userService;
	
	@KafkaListener(topics = "${ou-hrms.topic.userCreated}")
    public void handleNotification(UserCreatedEvent event) {
		try {
			System.out.println("#################### " + event.toString() + " ############################");
			userService.create(event);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    }
}
