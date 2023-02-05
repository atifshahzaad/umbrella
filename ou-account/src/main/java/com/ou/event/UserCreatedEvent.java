package com.ou.event;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.ou.dto.RoleDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserCreatedEvent implements Event{

	private UUID userId;
	private UUID supervisor;
	
	private String email;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private Set<RoleDTO> roles;
	
	
	
}
