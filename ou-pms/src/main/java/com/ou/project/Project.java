package com.ou.project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ou.common.User;
import com.ou.team.Team;

import lombok.Data;

@Data
@Document("project")
public class Project {

	@Id
	private String id;
	
	private String name;
	
	private String description;
	
	private User createdBy;
	
	private LocalDate startedAt;
	
	private LocalDateTime createdAt;
	
	@DBRef
	private Set<Team> teams;
	
}
