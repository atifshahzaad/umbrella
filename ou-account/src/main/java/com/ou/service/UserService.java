package com.ou.service;

import java.util.UUID;

import com.ou.dto.UserInfoDTO;
import com.ou.model.User;

public interface UserService {

	User create(User user);
	
	User update(UUID userId, UserInfoDTO dto);
}
