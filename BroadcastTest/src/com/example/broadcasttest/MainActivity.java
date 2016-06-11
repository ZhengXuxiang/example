package com.example.broadcasttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private IntentFilter intentFilter;
	private NetworkChangeReceiver networkChangeReceiver;
	
	//���ع㲥
	private LocalReceiver localReceiver;
	private LocalBroadcastManager LocalBroadcastManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ȡ���ع㲥ʵ��
		LocalBroadcastManager=LocalBroadcastManager.getInstance(this);
		
		//ע��㲥������
//		intentFilter =new IntentFilter();
//		intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//		networkChangeReceiver = new NetworkChangeReceiver();
//		registerReceiver(networkChangeReceiver, intentFilter);
		
		//ע�᱾�ع㲥������
		intentFilter = new IntentFilter();
		intentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
		localReceiver = new LocalReceiver();
		LocalBroadcastManager.registerReceiver(localReceiver, intentFilter);
		
		
		Button button =(Button)findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent =new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
				LocalBroadcastManager.sendBroadcast(intent);//���ͱ��ع㲥
//				sendOrderedBroadcast(intent, null);
			}
		});
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(networkChangeReceiver);
	}



	class NetworkChangeReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
			
			if(networkInfo!=null&&networkInfo.isAvailable()){
				Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(context,"network is unavailable",Toast.LENGTH_LONG).show();
			}
			
			
			Toast.makeText(context, "network changes", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	class LocalReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(context, "this is localbroadcast", Toast.LENGTH_SHORT).show();
		}
		
	}
}


















