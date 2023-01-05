package com.ou.event;

import lombok.Data;

@Data
public class UserCreatedEvent {

	private String name;
	private String email;
}
