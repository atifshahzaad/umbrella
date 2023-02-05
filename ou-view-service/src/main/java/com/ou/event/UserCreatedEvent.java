package com.ou.event;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.ou.model.Role;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserCreatedEvent {

	private UUID userId;
	private UUID supervisor;
	
	private String email;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private Set<Role> roles;
}
