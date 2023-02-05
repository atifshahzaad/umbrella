package com.ou.model;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class TeamMember {

	private UUID id;
	private String role;
	private String status;
	private String type;
	private LocalDate joinAt;
	private LocalDate leaveAt;
}
