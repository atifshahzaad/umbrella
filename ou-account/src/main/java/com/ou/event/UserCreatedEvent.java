package com.ou.event;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserCreatedEvent {

	private UUID id;
	private UUID supervisor;
	private LocalDate joiningDate;
	
	
	
}
