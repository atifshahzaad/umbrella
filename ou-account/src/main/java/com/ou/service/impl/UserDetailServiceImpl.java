package com.ou.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ou.dto.UserDetailDTO;
import com.ou.exceptions.ResourceNotFoundException;
import com.ou.model.Company;
import com.ou.model.UserDetail;
import com.ou.repository.UserDetailRepository;
import com.ou.service.UserDetailService;

@Service
public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	private UserDetailRepository userDetailRepository;

	@Override
	public UserDetail create(UserDetail userDetail) {
		return userDetailRepository.save(userDetail);
	}

	@Override
	public UserDetailDTO get(UUID id) {
		
		return userDetailRepository.get(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for provided id: " + id.toString()));

	}

	@Override
	public Company getUserCompany(UUID userId) {
		return userDetailRepository.getUserCompany(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Company not found for user with id: " + userId.toString()));
	}

}
