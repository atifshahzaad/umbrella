package com.ou.service;

import com.ou.event.UserCreatedEvent;

public interface EmailService {

	void handleUserCreatedEvent(UserCreatedEvent event);
}
