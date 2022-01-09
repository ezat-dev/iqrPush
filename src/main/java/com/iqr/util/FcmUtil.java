package com.iqr.util;

import java.io.FileInputStream;
import java.util.List;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.MulticastMessage;

public class FcmUtil {
	public void corr_FCM(List<String> tokenList, String title, String content, String send){
		try{

			FileInputStream refreshToken = new FileInputStream("D:/fcm_key/ez-iqr-firebase-adminsdk-yr8ui-6434316967.json");

			
			
			FirebaseOptions options = new FirebaseOptions.Builder()
										.setCredentials(GoogleCredentials.fromStream(refreshToken))
//										.setDatabaseUrl("https://samplepush-fe215.firebaseio.com")
										.build();
			
			if(FirebaseApp.getApps().isEmpty()){
				FirebaseApp.initializeApp(options);
			}
			
			
			
			
			MulticastMessage message = MulticastMessage.builder()
					.setAndroidConfig(AndroidConfig.builder()
					.setTtl(3600 * 1000)
					.setPriority(AndroidConfig.Priority.HIGH)
					
						.setNotification(AndroidNotification.builder()
							.setTag(send+" "+content)
							.setTitle(send+" "+content)
							.setBody(title+" : "+content)
							.setClickAction("")								
							.setSound("default")
							.build())		
						/* default, siren, heartbeat */
					
					.build())
					.putData("title",title)
					.putData("body",content)
					.putData("hogi",send)
					.addAllTokens(tokenList)
					.build();
			
			
			
			BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
