package com.ou.event;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserCreatedEvent {

	private UUID userId;
	private UUID supervisor;
	private String email;
	private LocalDate joiningDate;
	
}
