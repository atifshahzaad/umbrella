package com.ou.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ou.proxy.ouhrms.HrmsProxy;
import com.ou.proxy.ouhrms.dto.CreateUserDTO;

@Service
public class HRMSService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HRMSService.class);

	/*
	 * @Autowired private HrmsProxy hrmsProxy;
	 * 
	 * public boolean createUser(CreateUserDTO dto) {
	 * 
	 * LOGGER.info("Sending create user request to HRMS");
	 * 
	 * try { ResponseEntity<Void> response = hrmsProxy.create(dto);
	 * 
	 * LOGGER.info("Recived HRMS create user response with status code: " +
	 * response.getStatusCode());
	 * 
	 * if (response.getStatusCode().equals(HttpStatus.CREATED)) return true; } catch
	 * (Exception e) {
	 * LOGGER.error("Exception occured at HRMS user creation. Message: " +
	 * e.getMessage()); }
	 * 
	 * return false; }
	 */

}
