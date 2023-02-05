package com.ou.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "ou_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private UUID id;

	@Column(name="first_name", nullable = true)
	private String firstName;
	
	@Column(name="middle_name", nullable = true)
	private String middleName;
	
	@Column(name="last_name", nullable = true)
	private String lastName;
	
	@Column(name="email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "serial_number", nullable = false)
	private int serialNumber;

	@Column(name = "phone", nullable = true)
	private String phone;

	@Column(name = "phone2", nullable = true)
	private String phone2;

	@Column(name = "gender", nullable = true)
	private String gender;

	@Column(name = "marital_status", nullable = true)
	private String maritalStatus;

	@Column(name = "status", nullable = true)
	private String status;

	@Column(name = "birth_date", nullable = true)
	private LocalDate birthDate;

	@Column(name = "joining_date", nullable = true)
	private LocalDate joiningDate;

	@Column(name = "profile_picture", nullable = true)
	private String picture;

	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Basic(optional = false)
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supervisor", referencedColumnName = "id")
	private User supervisor;

	public User(UUID id) {
		this.id = id;
	}

	public User(UUID id, LocalDate joiningDate, User supervisor) {
		this.id = id;
		this.joiningDate = joiningDate;
		this.supervisor = supervisor;
	}

	public User(UUID id, LocalDate joiningDate) {
		this.id = id;
		this.joiningDate = joiningDate;
	}

	public User(UUID id, String email, LocalDate joiningDate) {
		this.id = id;
		this.email = email;
		this.joiningDate = joiningDate;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

}
