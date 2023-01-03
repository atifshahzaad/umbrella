package com.ou.proxy.ouhrms.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserDTO {

	private UUID id;
	private UUID supervisor;
	private LocalDate joiningDate;

	public CreateUserDTO(UUID id) {
		this.id = id;
	}

	public CreateUserDTO(UUID id, UUID supervisor) {
		this.id = id;
		this.supervisor = supervisor;
	}
}
