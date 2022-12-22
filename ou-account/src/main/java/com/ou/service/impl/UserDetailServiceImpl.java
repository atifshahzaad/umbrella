package com.ou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
