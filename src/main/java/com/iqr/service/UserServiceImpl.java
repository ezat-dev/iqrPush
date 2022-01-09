package com.iqr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqr.dao.UserDao;
import com.iqr.domain.Pushs;
import com.iqr.domain.Users;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	

	@Override
	public List<String> getUserTokenList(String u_company) {
		return userDao.getUserTokenList(u_company);
	}

	@Override
	public List<Users> getUserList() {
		return userDao.getUserList();
	}

	@Override
	public List<Users> getPushSendAlarmDetailList() {
		return userDao.getPushSendAlarmDetailList();
	}

}
