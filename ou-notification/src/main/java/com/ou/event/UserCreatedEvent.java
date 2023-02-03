package com.ou.event;

import lombok.Data;

@Data
public class UserCreatedEvent {

	private String name;
	private String password;
	private String email;
	private boolean sendPasswordEmail;
}
