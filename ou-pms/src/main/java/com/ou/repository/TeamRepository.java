package com.ou.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ou.model.Team;

public interface TeamRepository extends MongoRepository<Team, String>{

}
