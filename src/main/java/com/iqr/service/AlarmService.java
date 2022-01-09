package com.iqr.service;

import java.util.List;
import java.util.Map;

import com.iqr.domain.Alarms;

public interface AlarmService {
	List<Alarms> getPushSendAlarmList();

	void pushAlarmRegist(Map<String, Object> param);
}
