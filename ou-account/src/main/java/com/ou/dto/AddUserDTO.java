package com.ou.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class AddUserDTO {
	private String email;
	private List<UUID> roles;
	private UUID supervisior;
}
