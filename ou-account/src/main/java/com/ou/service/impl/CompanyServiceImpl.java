package com.ou.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ou.dto.CreateCompanyDTO;
import com.ou.model.Company;
import com.ou.model.Country;
import com.ou.model.Role;
import com.ou.model.User;
import com.ou.model.UserDetail;
import com.ou.model.UserRole;
import com.ou.repository.CompanyRepository;
import com.ou.service.CompanyService;
import com.ou.service.CountryService;
import com.ou.service.RoleService;
import com.ou.service.UserDetailService;
import com.ou.service.UserRoleService;
import com.ou.service.UserService;
import com.ou.util.OuAccountUtil;

import jakarta.transaction.Transactional;

@Service
public class CompanyServiceImpl implements CompanyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Override
	@Transactional
	public Company create(CreateCompanyDTO dto) {
		
		LOGGER.info("company creation started");
		
		Country country = countryService.findByIdForReference(dto.getCountryId());
		Company company = modelMapper.map(dto, Company.class);
		company.setCountry(country);
		Company insertedCompany = companyRepository.save(company);
		
		LOGGER.info("company is created");
		LOGGER.info("start creating user");
		
		User user = modelMapper.map(dto, User.class);
		User persistedUser = userService.create(user);
		
		LOGGER.info("user is created");
		LOGGER.info("start creating user detail");
		
		UserDetail userDetail = new UserDetail(persistedUser, insertedCompany);
		userDetailService.create(userDetail);
		
		LOGGER.info("user detail is created");
		
		Role role = roleService.findByNameForReference(OuAccountUtil.ADMIN_ROLE);
		
		UserRole userRole = new UserRole(persistedUser, role);
		userRoleService.create(userRole);
		
		LOGGER.info("company creation ended");
		
		return insertedCompany;
	}

}
