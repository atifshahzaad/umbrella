package com.ou.model;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document("team")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team {

	@Id
	private String id;
	private String name;
	private LocalDateTime createdAt;
	private Set<TeamMember> members;
}
