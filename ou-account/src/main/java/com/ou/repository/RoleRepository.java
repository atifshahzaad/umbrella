package com.ou.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ou.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

	@Query("SELECT new com.ou.model.Role(r.id, r.name) FROM Role r WHERE r.id=:id")
	Optional<Role> findByIdForReference(@Param("id") UUID id);

	@Query("SELECT new com.ou.model.Role(r.id, r.name) FROM Role r WHERE r.name=:name")
	Optional<Role> findByNameForReference(@Param("name") String name);
}
