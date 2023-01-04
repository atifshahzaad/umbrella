package com.ou.event;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class UserCreatedEvent {

	private UUID id;
	private UUID supervisor;
	private LocalDate joiningDate;
	
}
