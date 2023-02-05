package com.ou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ou.model.Team;
import com.ou.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;
	
	public Team create(Team team) {
		return teamRepository.save(team);
	}
	
}
