package com.ou.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ou.dto.User;

import lombok.Data;

@Data
@Document("project")
public class Project {

	@Id
	private String id;
	
	private String name;
	
	private String description;
	
	private User createdBy;
	
	private LocalDate startDate;
	
	private LocalDateTime createdAt;
	
	@DBRef
	private Set<Team> teams;
	
	@Transient
	private String projectId;
	
}
