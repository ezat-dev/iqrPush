package com.iqr.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqr.domain.Users;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<String> getUserTokenList(String u_company) {
		return sqlSession.selectList("users.getUserTokenList",u_company);
	}

	@Override
	public List<Users> getUserList() {
		return sqlSession.selectList("users.getUserList");
	}
	
	
	
}
