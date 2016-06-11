package com.example.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button)findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				NotificationManager manager =(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
				@SuppressWarnings("deprecation")
				Notification notice = new Notification(R.drawable.ic_launcher,"This is content",System.currentTimeMillis());
				Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
				PendingIntent pi = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
				notice.setLatestEventInfo(getBaseContext(), "title", "这里是内容的啊飞飞否定假粉而非飞额", pi);
				manager.notify(1, notice);
			}
		});
	}
}
