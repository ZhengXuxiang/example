package com.example.servicebestpractice;

import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class LongRunningService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Log.d("LongRunningService","execute at"+new Date().toString());
			}
		}).start();
		AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
		int anHour = 5000;//这是一个分钟的毫秒数
		long triggerAtTime = SystemClock.elapsedRealtime()+anHour;
		Intent i = new Intent(this,AlarmReceiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(this,0,i,0);
		manager.set(AlarmManager.RTC_WAKEUP,triggerAtTime,pi);
		return super.onStartCommand(intent, flags, startId);
		
	}
	
	
}
