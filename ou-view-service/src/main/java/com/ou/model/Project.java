package com.ou.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("project")
public class Project {

	@Id
	private String id;
	private String projectId;
	private String name;
	private String description;
	private LocalDate startDate;
	private LocalDateTime createdAt;
	
	Set<Team> teams;

	@DBRef
	private User createdBy;

}
