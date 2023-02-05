package com.ou.event;

import java.util.Set;

import com.ou.dto.RoleDTO;

import lombok.Data;

@Data
public class UserRoleUpdatedEvent implements Event {

	private Set<RoleDTO> roles;

}
