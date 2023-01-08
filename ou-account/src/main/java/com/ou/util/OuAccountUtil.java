package com.ou.util;

import java.util.UUID;

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

	public static String generateRandomPassword() {
		String text = UUID.randomUUID().toString();
		String[] arr = text.split("-");
		return arr[arr.length - 1];
	}
	
	public static UUID getId(String subject) {
		String[] split = subject.split(":");
		return UUID.fromString(split[split.length - 1]);
	}

}
