package com.ou.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ou.dto.AddUserDTO;
import com.ou.dto.CreateCompanyDTO;
import com.ou.event.UserCreatedEvent;
import com.ou.exceptions.ResourceNotFoundException;
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

	/*
	 * @Autowired private HRMSEventService hrmsEventService;
	 */

	/*
	 * @Autowired private PasswordEncoder passwordEncoder;
	 */

	@Override
	@Transactional
	public Company create(CreateCompanyDTO dto) {

		LOGGER.info("company creation started");

		Country country = countryService.findByIdForReference(dto.getCountryId());
		Company company = modelMapper.map(dto, Company.class);
		company.setCountry(country);
		Company persistedCompany = companyRepository.save(company);

		LOGGER.info("company is created");
		LOGGER.info("start creating user");

		User user = modelMapper.map(dto, User.class);
		// user.setPassword(passwordEncoder.encode(dto.getPassword()));
		User persistedUser = userService.create(user);

		LOGGER.info("user is created");
		LOGGER.info("start creating user detail");

		UserDetail userDetail = new UserDetail(persistedUser, persistedCompany);
		userDetailService.create(userDetail);

		LOGGER.info("user detail is created");

		Role role = roleService.findByNameForReference(OuAccountUtil.ADMIN_ROLE);

		UserRole userRole = new UserRole(persistedUser, role);
		userRoleService.create(userRole);

		LOGGER.info("company creation ended");

		UserCreatedEvent event = UserCreatedEvent.builder().id(persistedUser.getId()).build();

		// hrmsEventService.sendUserCreatedEvent(event);

//		CreateUserDTO cDto = new CreateUserDTO();
//		cDto.setId(persistedUser.getId());
//		boolean response = hrmsService.createUser(cDto);
//		if(!response) {
//			LOGGER.info("Saving HRMS creating user failure");
//		} else {
//			LOGGER.info("HRMS created user successfully");
//		}

		return persistedCompany;
	}

	@Override
	public Company findCompanyByIdForReference(UUID id) {
		return companyRepository.findCompanyByIdForReference(id).orElseThrow(
				() -> new ResourceNotFoundException("Company not founded with provided id: " + id.toString()));
	}

	@Override
	@Transactional
	public void addUser(AddUserDTO dto) {

		Company company = this.findCompanyByIdForReference(dto.getCompany());

		User user = new User(dto.getEmail());
		User persistedUser = userService.create(user);

		UserDetail userDetail = new UserDetail(persistedUser, company);
		userDetailService.create(userDetail);

		Role role = roleService.findByIdForReference(dto.getRole());

		UserRole userRole = new UserRole(persistedUser, role);
		userRoleService.create(userRole);

		UserCreatedEvent event = UserCreatedEvent
				.builder().id(persistedUser.getId()).email(persistedUser.getEmail()).name(persistedUser.getFirstName()
						+ " " + persistedUser.getMiddleName() + " " + persistedUser.getLastName())
				.supervisor(dto.getSupervisior()).build();

		// hrmsEventService.sendUserCreatedEvent(event);

	}

}
