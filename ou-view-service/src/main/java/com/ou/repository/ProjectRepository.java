package com.ou.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ou.model.Project;

public interface ProjectRepository extends MongoRepository<Project, String>{

}
