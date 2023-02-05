package com.ou.dto;

import java.time.LocalDate;

import com.ou.annotation.EnumValidator;
import com.ou.enumtype.Gender;
import com.ou.enumtype.MaritalStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserDTO {

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
	
	@Size(min = 11, max = 15, message = "phone must be 1 to 20 characters long")
	private String phone;
	
	@Size(min = 11, max = 15, message = "phone2 must be 1 to 20 characters long")
	private String phone2;
	
	@EnumValidator(target = Gender.class, message = "gender must from [Male, FeMale]")
	private String gender;
	
	@EnumValidator(target = MaritalStatus.class, message = "maritalStatus must from [Single, Married]")
	private String maritalStatus;
	
	private LocalDate birthDate;
	

}
