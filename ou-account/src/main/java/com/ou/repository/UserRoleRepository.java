package com.ou.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ou.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID>{

}
