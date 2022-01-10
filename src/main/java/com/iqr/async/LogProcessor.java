package com.iqr.async;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.iqr.domain.Alarms;
import com.iqr.domain.Pushs;
import com.iqr.domain.Users;
import com.iqr.service.AlarmService;
import com.iqr.service.UserService;
import com.iqr.util.FcmUtil;

public class LogProcessor {
	
	@Autowired
	private AlarmService alarmService;
	
	@Autowired
	private UserService userService;
	
	//주기별 실행 60000 = 1분
	@Scheduled(fixedRate = 60000)
	public void handle() throws InterruptedException {
//		System.out.println("11");
		FcmUtil fcmUtil = new FcmUtil();

		
//		System.out.println("22");
		//EZAT 사용자 목록
		List<String> tokenList = null;
		
		//
//		System.out.println("33");
		
		List<Users> userList = userService.getUserList();
		
//		System.out.println("유저 카운트 : "+userList.size());
		
		//검교정 설정 알람(푸시알람을 보내야 하는 목록)
		List<Users> alarmList = userService.getPushSendAlarmDetailList();
//		System.out.println("44");
//		System.out.println("알람 카운트 : "+alarmList.size());
		
/*
		List<Alarms> alarmList2 = alarmService.getPushSendAlarmList();
		System.out.println("알람 카운트2 : "+alarmList2.size());
*/
		
		if(alarmList.size() > 0) {
			for(int i=0; i<alarmList.size(); i++) {
/*				
				System.out.println("알람명 : "+alarmList.get(i).getAlarm_name());
				System.out.println("알람시간 : "+alarmList.get(i).getAlarm_time());
				System.out.println("알람거래처 : "+alarmList.get(i).getU_company());
*/
				
				for(int j=0; j<userList.size(); j++) {
					//발생한 알람을 받을 사람이 있는지 체크
					tokenList = userService.getUserTokenList(alarmList.get(i).getU_company());	
					
					if(tokenList.size() != 0) {					
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("qr_code", alarmList.get(i).getQr_code());
						param.put("alarm_name",alarmList.get(i).getAlarm_name());
						param.put("alarm_time",alarmList.get(i).getAlarm_time());
						param.put("alarm_sdate", alarmList.get(i).getAlarm_sdate());
						alarmService.pushAlarmRegist(param);
						
						//아래 메소드는 사용자 테이블에 token_id 업데이트 한 다음 테스트
					
						fcmUtil.corr_FCM(tokenList, 
								alarmList.get(i).getAlarm_time(),
								alarmList.get(i).getAlarm_name(),
								alarmList.get(i).getU_company()
								);
					}
				}
				
				
/*				
				for(int z=0; z<tokenList.size(); z++) {
					System.out.println("alarmList.get(i).getU_company() => ["+(z+1)+"] : "+tokenList.get(z));
				}
*/				
				
						
			}			
			
		}
		
	}
}
