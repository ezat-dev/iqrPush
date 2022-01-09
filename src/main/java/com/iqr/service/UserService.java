package com.iqr.service;

import java.util.List;

import com.iqr.domain.Users;

public interface UserService {
	List<String> getUserTokenList(String u_company);

	List<Users> getUserList();
}
