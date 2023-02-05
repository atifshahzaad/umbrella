package com.ou.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ou.dto.CreateProjectDTO;
import com.ou.event.producer.EventProducer;
import com.ou.model.MemberStatus;
import com.ou.model.MemberType;
import com.ou.model.Project;
import com.ou.model.Team;
import com.ou.model.TeamMember;
import com.ou.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private EventProducer eventProducer;

	public Project create(CreateProjectDTO dto) {
		Project project = modelMapper.map(dto, Project.class);
		project.setCreatedAt(LocalDateTime.now());
		Team team = null;
		if(Optional.ofNullable(dto.getManager()).isPresent()) {
			TeamMember teamMember = TeamMember.builder()
					.id(dto.getManager())
					.role(TeamMember.ROLE_MANAGER)
					.status(MemberStatus.ACTIVE).type(MemberType.PERMANENT).joinAt(LocalDate.now()).build();

			team = Team.builder().name(String.format("%s Team", dto.getName())).createdAt(LocalDateTime.now())
					.members(Set.of(teamMember)).build();
			
			team = teamService.create(team);
		}

		if (Optional.ofNullable(team).isPresent())
			project.setTeams(Set.of(team));

		Project persistedProject = projectRepository.save(project);
		
		project.setProjectId(project.getId());
		
		eventProducer.sendProjectCreatedEvent(persistedProject);
		
		return persistedProject;
	}

}
