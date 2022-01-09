package com.iqr.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqr.dao.AlarmDao;
import com.iqr.domain.Alarms;
import com.iqr.domain.Pushs;

@Service
public class AlarmServiceImpl implements AlarmService{

	@Autowired
	private AlarmDao alarmDao;

	@Override
	public List<Alarms> getPushSendAlarmList() {
		return alarmDao.getPushSendALarmList();
	}

	@Override
	public void pushAlarmRegist(Map<String, Object> param) {
		alarmDao.pushAlarmRegist(param);
	}
}
