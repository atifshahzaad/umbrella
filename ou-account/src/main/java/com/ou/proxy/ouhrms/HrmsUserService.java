package com.ou.proxy.ouhrms;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ou.config.LoadBalancerConfiguration;
import com.ou.proxy.ouhrms.dto.CreateUserDTO;

@FeignClient(name = "ou-hrms")
@LoadBalancerClient(name = "ou-hrms", configuration = LoadBalancerConfiguration.class)
public interface HrmsUserService {

	@PostMapping("ouh/api/v1/user")
	ResponseEntity<Void> create(@RequestBody CreateUserDTO dto);
}
