package com.ou.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ou.exceptions.ResourceNotFoundException;
import com.ou.model.Role;
import com.ou.repository.RoleRepository;
import com.ou.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role findByIdForReference(UUID id) {
		return roleRepository.findByIdForReference(id).orElseThrow( () -> new ResourceNotFoundException("Role not found for provided id: " + id.toString()) );
	}

	@Override
	public Role findByNameForReference(String name) {
		return roleRepository.findByNameForReference(name).orElseThrow( () -> new ResourceNotFoundException("Role not found for provided name: " + name) );
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role create(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void delete(UUID id) {
		roleRepository.deleteById(id);
	}

}
