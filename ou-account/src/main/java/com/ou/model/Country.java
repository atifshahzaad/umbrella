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
@Table(name = "country")
public class Country extends Thing implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "iso3", nullable = false, unique = true)
	private String iso3;
	
	public Country(UUID id) {
		super.setId(id);
	}
}
