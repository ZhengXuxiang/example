package com.example.androidthreadtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public static final int UPDATE_TEXT=1;
	
	private  TextView  text;
	
	private Handler handler =new Handler(){
		public void handleMessage(Message msg){
			switch (msg.what) {
			case UPDATE_TEXT:
				//���������UI����
				text.setText("��ð�");
				break;
			default:
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button)findViewById(R.id.button);
		text = (TextView)findViewById(R.id.text);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.button:
					new Thread(new Runnable() {
						@Override
						public void run() {
							Message message=new Message();
							message.what=UPDATE_TEXT;
							handler.sendMessage(message);//��Message�����ͳ�ȥ
						}
					}).start();
					break;

				default:
					break;
				}
			}
		});
	}
}
