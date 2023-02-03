package com.ou.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailDTO {

	private UUID id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String picture;
	private boolean regCompleted;
	private UUID companyId;
}
