package com.ou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ou.dto.CreateProjectDTO;
import com.ou.model.Project;
import com.ou.service.ProjectService;

@RestController
@RequestMapping("oupms/api/v1/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@PostMapping
	public ResponseEntity<Project> create(@RequestBody CreateProjectDTO dto) {
		Project project = projectService.create(dto);
		return new ResponseEntity<Project>(project, HttpStatus.CREATED);
	}
}
