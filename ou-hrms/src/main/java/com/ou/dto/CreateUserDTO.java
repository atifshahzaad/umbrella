package com.ou.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class CreateUserDTO {

	private UUID id;
	private UUID supervisor;
	private LocalDate joiningDate;

}
