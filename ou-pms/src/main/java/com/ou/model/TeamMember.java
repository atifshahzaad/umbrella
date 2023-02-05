package com.ou.model;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamMember {

	public static final String ROLE_MANAGER = "MANAGER";
	
	private UUID id;
	private String role;
	private MemberStatus status;
	private MemberType type;
	private LocalDate joinAt;
	private LocalDate leaveAt;
}
