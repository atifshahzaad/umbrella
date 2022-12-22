package com.ou.service;

import com.ou.dto.CreateCompanyDTO;
import com.ou.model.Company;

public interface CompanyService {

	Company create(CreateCompanyDTO dto);
}
