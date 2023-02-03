package com.ou.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSearchResultDTO {

	private UUID id;
	private String name;
	private String role;
}
