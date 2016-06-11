package com.example.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	
	private Button startService;
	private Button stopService;
	private Button bindService;
	private Button unbindService;
	private MyService.DownloadBinder downloadBinder;
	private ServiceConnection connection=new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
		}
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			downloadBinder =(MyService.DownloadBinder)service;
			downloadBinder.startDownload();
			downloadBinder.getProgress();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startService=(Button)findViewById(R.id.startService);
		stopService=(Button)findViewById(R.id.stopService);
		bindService=(Button)findViewById(R.id.bindService);
		unbindService=(Button)findViewById(R.id.unbindService);
		startService.setOnClickListener(this);
		stopService.setOnClickListener(this);
		bindService.setOnClickListener(this);
		unbindService.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.startService:
			Intent startIntent = new Intent(this,MyService.class);
			startService(startIntent);//启动服务
			break;
		case R.id.stopService:
			Intent stopIntent = new Intent(this,MyService.class);
			stopService(stopIntent);
			break;
		case R.id.bindService:
			Intent bindIntent = new Intent(this,MyService.class);
			bindService(bindIntent,connection,BIND_AUTO_CREATE);//绑定服务
			break;
		case R.id.unbindService:
			unbindService(connection);//解除绑定
			break;
		default:
			break;
		}
	}
}













