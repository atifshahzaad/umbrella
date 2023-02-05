package com.ou.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role extends Thing implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	public Role(UUID id) {
		super.setId(id);
	}
	
	public Role(UUID id, String name) {
		super.setId(id);
		this.name = name;
	}

}
