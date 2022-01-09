package com.iqr.dao;

import java.util.List;
import java.util.Map;

import com.iqr.domain.Alarms;

public interface AlarmDao {

	List<Alarms> getPushSendAlarmList();

	void pushAlarmRegist(Map<String, Object> param);

}
