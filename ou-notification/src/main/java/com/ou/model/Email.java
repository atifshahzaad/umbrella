package com.ou.model;

import java.io.Serializable;

import com.ou.event.UserCreatedEvent;
import com.ou.util.OUNotificationUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "email")
public class Email extends Thing implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="type", nullable = false)
	private String type;
	
	@Column(name="to_email", nullable = false)
	private String toEmail;
	
	@Column(name="to_name", nullable = false)
	private String toName;
	
	@Column(name="data", nullable = false)
	private String data;
	
	@Column(name="completed")
	private boolean completed;
	
	public Email(UserCreatedEvent event, String data) {
		this.type = OUNotificationUtil.FIRST_TIME_PASSWORD_RESET;
		this.toEmail = event.getEmail();
		this.toName = event.getName();
		this.data = data;
		this.completed = false;
	}

}
