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
		//ȥ������,������setContentView����ǰ
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		//����һ�����߳�,while(true)ѭ�����Ͷ���
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
//				ѭ������
//				Thread.sleep(1000);
				SystemClock.sleep(1000);
				//���Ź�����
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(
						"5556", //�ռ��˺���
						null,//�������ĺ��� 
						"fdsfdfdsf", 
						null,//������ͳɹ�,�ص��˹㲥,֪ͨ���� 
						null);//���Է����ܳɹ�,�ص��˹㲥
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
