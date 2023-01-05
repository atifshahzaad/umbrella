package com.ou.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.ou.event.UserCreatedEvent;
import com.ou.service.EmailService;

@Component
public class EventListener {
	
	@Autowired
	private EmailService emailService;
	
	@KafkaListener(topics = "${ou_notification.topic.userCreated}")
    public void handleNotification(UserCreatedEvent event) {
		System.out.println("#################### " + event.getEmail() + " ############################");
		emailService.handleUserCreatedEvent(event);
    }
}
