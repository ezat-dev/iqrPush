package com.iqr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqr.domain.Alarms;

@Repository
public class AlarmDaoImpl implements AlarmDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Alarms> getPushSendAlarmList() {
		System.out.println("Dao : getPushSendAlarmList");
		
		return sqlSession.selectList("alarms.getPushSendAlarmList");
	}

	@Override
	public void pushAlarmRegist(Map<String, Object> param) {
		sqlSession.insert("alarms.pushAlarmRegist",param);
	}



}
