package com.ou.team;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("team")
public class Team {

	@Id
	private String id;
	private String name;
	private LocalDateTime createdAt;
	private Set<TeamMember> members;
}
