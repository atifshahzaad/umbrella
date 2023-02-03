package com.ou.project;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ou.project.dto.CreateProjectDTO;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Project create(CreateProjectDTO dto) {
		Project project = modelMapper.map(dto, Project.class);
		project.setCreatedAt(LocalDateTime.now());
		return projectRepository.save(project);
	}

}
