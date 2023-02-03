package com.ou.service;

import java.util.UUID;

import com.ou.dto.UserDetailDTO;
import com.ou.model.Company;
import com.ou.model.UserDetail;

public interface UserDetailService {

	UserDetailDTO get(UUID id);

	UserDetail create(UserDetail userDetail);
	
	Company getUserCompany(UUID userId);
}
