package com.ou.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OuAccountUtil {

	public static String ADMIN_ROLE;

	@Autowired
	public void loadOnelinkConfig(@Value("${ou_account.admin_role}") String adminRole) {
		ADMIN_ROLE = adminRole;
	}

}
