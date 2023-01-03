package com.ou.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ou_user")
public class User extends Thing implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="first_name", nullable = true)
	private String firstName;
	
	@Column(name="middle_name", nullable = true)
	private String middleName;
	
	@Column(name="last_name", nullable = true)
	private String lastName;
	
	@Column(name="email", nullable = false, unique = true)
	private String email;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="profile_picture", nullable = true)
	private String picture;
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

}
