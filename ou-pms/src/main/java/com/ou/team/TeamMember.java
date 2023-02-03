package com.ou.team;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TeamMember {

	private String memberId;
	private String role;
	private String status;
	private LocalDate joinAt;
	private LocalDate leaveAt;
}
