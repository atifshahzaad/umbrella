package com.ou.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserInfoDTO {

	@NotNull(message = "firstName cannot be null")
	@NotBlank(message = "firstName cannot be empty")
	@Size(min = 1, max = 20, message = "firstName must be 1 to 20 characters long")
	private String firstName;
	
	@Size(min = 0, max = 20, message = "middleName must be 0 to 20 characters long")
	private String middleName;
	
	@NotNull(message = "lastName cannot be null")
	@NotBlank(message = "lastName cannot be empty")
	@Size(min = 1, max = 20, message = "lastName must be 1 to 20 characters long")
	private String lastName;
	
	@NotNull(message = "password cannot be null")
	@NotBlank(message = "password cannot be empty")
	@Size(min = 6, max = 16, message = "password must be 6 to 16 characters long")
	private String password;
	
}
