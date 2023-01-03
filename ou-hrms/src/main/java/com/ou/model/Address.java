package com.ou.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address extends Thing implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "address_line_1", nullable = false)
	private String addressLine1;

	@Column(name = "address_line_2", nullable = true)
	private String addressLine2;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "zip", nullable = false)
	private String zip;

	@Column(name = "is_primary")
	private boolean isPrimary;

	@ManyToOne
	@JoinColumn(name = "ou_user", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "country", referencedColumnName = "id")
	private Country Country;

}
