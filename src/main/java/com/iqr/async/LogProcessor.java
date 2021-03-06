package com.iqr.async;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.iqr.domain.Users;
import com.iqr.service.AlarmService;
import com.iqr.service.UserService;
import com.iqr.util.FcmUtil;

public class LogProcessor {
	
	@Autowired
	private AlarmService alarmService;
	
	@Autowired
	private UserService userService;
	
	//주기별 실행 60000 = 1분 / 10분 변경 fixedRate = 600000
	//월-금 오전 9시
	@Scheduled(cron="0 0 9 ? * MON-FRI")
//	@Scheduled(fixedRate = 60000)
	public void handle() throws InterruptedException {
		FcmUtil fcmUtil = new FcmUtil();

		
		//EZAT 사용자 목록
		List<String> tokenList = null;
		
		//각 거래처별 전체 활성화된 사용자의 목록
		List<Users> userList = userService.getUserList();
		
		//검교정 설정 알람(푸시알람을 보내야 하는 목록)
		List<Users> alarmList = userService.getPushSendAlarmDetailList();
		
		if(alarmList.size() > 0) {
			for(int i=0; i<alarmList.size(); i++) {

				Map<String, Object> param = new HashMap<String, Object>();
				param.put("qr_code", alarmList.get(i).getQr_code());
				param.put("alarm_name",alarmList.get(i).getAlarm_name());
				param.put("alarm_time",alarmList.get(i).getAlarm_time());
				param.put("alarm_sdate", alarmList.get(i).getAlarm_sdate());
				
				alarmService.pushAlarmRegist(param);

				for(int j=0; j<userList.size(); j++) {
					//발생한 알람을 받을 사람이 있는지 체크
					tokenList = userService.getUserTokenList(alarmList.get(i).getU_company());	
					
					if(tokenList.size() != 0) {
						//아래 메소드는 사용자 테이블에 token_id 업데이트 한 다음 테스트
					
						fcmUtil.corr_FCM(tokenList, 
								alarmList.get(i).getAlarm_time(),
								alarmList.get(i).getAlarm_name(),
								alarmList.get(i).getU_company()
								);
					
					}
				}		
			}			
			
		}
		
	}
}
