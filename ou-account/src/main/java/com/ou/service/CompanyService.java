package com.ou.service;

import java.util.UUID;

import com.ou.dto.AddUserDTO;
import com.ou.dto.CreateCompanyDTO;
import com.ou.model.Company;

public interface CompanyService {

	Company create(CreateCompanyDTO dto);
	
	Company findCompanyByIdForReference(UUID id);
	
	void addUser(UUID userId, AddUserDTO dto);
}
