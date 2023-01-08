package com.ou.service;

import com.ou.dto.LoginDTO;
import com.ou.dto.LoginResponse;

public interface AuthService {

	LoginResponse login(LoginDTO dto);
}
