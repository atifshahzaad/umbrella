package com.ou.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_detail")
public class UserDetail extends Thing implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name="id", referencedColumnName = "id")
	@MapsId
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "company", referencedColumnName = "id")
	private Company company;
	
	public UserDetail(User user, Company company) {
		this.user = user;
		this.company = company;
	}

}
