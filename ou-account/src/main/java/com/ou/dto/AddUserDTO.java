package com.ou.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class AddUserDTO {
	private UUID company;
	private String email;
	private UUID role;
	private UUID supervisior;
}
