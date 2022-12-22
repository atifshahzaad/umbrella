package com.ou.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CreateCompanyDTO {

	private String name;
	private String email;
	private String phone;
	private String addressLine1;
	private String addressLine2;
	private String password;
	private String city;
	private String firstName;
	private String middleName;
	private String lastName;
	private UUID countryId;
	
}
