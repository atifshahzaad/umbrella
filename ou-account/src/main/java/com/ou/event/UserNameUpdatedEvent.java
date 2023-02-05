package com.ou.event;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserNameUpdatedEvent implements Event{

	private UUID userId;
	
	private String firstName;
	private String middleName;
	private String lastName;
	
	private LocalDateTime updatedAt;
	
}
