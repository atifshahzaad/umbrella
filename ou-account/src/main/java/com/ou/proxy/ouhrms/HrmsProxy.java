package com.ou.proxy.ouhrms;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ou.proxy.ouhrms.dto.CreateUserDTO;

//@FeignClient(name = "ou-hrms")
//@LoadBalancerClient(name = "ou-hrms", configuration = LoadBalancerConfiguration.class)
public interface HrmsProxy {

	@PostMapping("ouh/api/v1/user")
	ResponseEntity<Void> create(@RequestBody CreateUserDTO dto);
}
