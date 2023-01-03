package com.ou.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ou.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

	@Query("SELECT new com.ou.model.User(u.id) FROM User u WHERE u.id=:id")
	Optional<User> findByIdForReference(@Param("id") UUID id);
}
