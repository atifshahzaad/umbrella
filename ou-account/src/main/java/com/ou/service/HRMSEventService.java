package com.ou.service;

import com.ou.event.UserCreatedEvent;

public interface HRMSEventService {

	void sendUserCreatedEvent(UserCreatedEvent event);
	
}
