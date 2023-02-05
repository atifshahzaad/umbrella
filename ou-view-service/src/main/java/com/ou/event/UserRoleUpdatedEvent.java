package com.ou.event;

import java.util.Set;
import java.util.UUID;

import com.ou.model.Role;

import lombok.Data;

@Data
public class UserRoleUpdatedEvent {
    private UUID userId;
    private Set<Role> roles;
}
