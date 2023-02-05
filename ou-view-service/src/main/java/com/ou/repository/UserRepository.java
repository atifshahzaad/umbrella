package com.ou.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ou.model.User;

public interface UserRepository extends MongoRepository<User, String>{

	Optional<User> findByUserId(String id);
	
}
