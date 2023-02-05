package com.ou.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Data
@Document("user")
@ToString
public class User {

	@Id
	private String id;
	
	private String userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String phone;
	private String phone2;
	private String gender;
	private String maritalStatus;
	private String status;
	private String picture;
	
	private UUID supervisor;
	
	private int serialNumber;
	
	private LocalDate birthDate;
	private LocalDate joiningDate;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	Set<Role> roles;
}
