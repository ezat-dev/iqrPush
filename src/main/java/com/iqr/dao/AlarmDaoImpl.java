package com.iqr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqr.domain.Alarms;
import com.iqr.domain.Pushs;

@Repository
public class AlarmDaoImpl implements AlarmDao{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Alarms> getPushSendALarmList() {
		return sqlSession.selectList("alarms.getPushSendALarmList");
	}

	@Override
	public void pushAlarmRegist(Map<String, Object> param) {
		sqlSession.insert("alarms.pushAlarmRegist", param);
	}
}
