package com.iqr.dao;

import java.util.List;
import java.util.Map;

import com.iqr.domain.Alarms;
import com.iqr.domain.Pushs;

public interface AlarmDao {
	
	List<Alarms> getPushSendALarmList();

	void pushAlarmRegist(Map<String, Object> param);

}
