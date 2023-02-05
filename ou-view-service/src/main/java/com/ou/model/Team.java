package com.ou.model;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;

@Data
public class Team {

	private String id;
	private String name;
	private LocalDateTime createdAt;
	private Set<TeamMember> members;
}
