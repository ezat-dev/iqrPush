package com.iqr.dao;

import java.util.List;

import com.iqr.domain.Users;

public interface UserDao {

	List<String> getUserTokenList(String u_company);

	List<Users> getUserList();

}
