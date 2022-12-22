package com.ou.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole  extends Thing implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "ou_user", referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "role", referencedColumnName = "id")
	private Role role;
	
	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}

}
