package com.iqr.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqr.dao.AlarmDao;
import com.iqr.domain.Alarms;

@Service
public class AlarmServiceImpl implements AlarmService{

	@Autowired
	private AlarmDao alarmDao;
	
	@Override
	public List<Alarms> getPushSendAlarmList() {
		System.out.println("Service : getPushSendAlarmList");
		return alarmDao.getPushSendAlarmList();
	}

	@Override
	public void pushAlarmRegist(Map<String, Object> param) {
		alarmDao.pushAlarmRegist(param);
	}

}
