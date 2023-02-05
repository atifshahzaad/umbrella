package com.ou.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class CreateProjectDTO {

	private String name;
	private String description;
	private UUID manager;
	private LocalDate startDate;
	
}
