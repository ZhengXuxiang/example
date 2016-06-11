package com.example.smstest;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	
	private TextView sender;
	private TextView content;
	
	//获取短信
	private IntentFilter intentFilter;
	private MessageReceiver messageReceiver;
	
	//点击发送时的添加的参数,发送按钮
	private EditText to;
	private EditText msgInput;
	private Button button;
	
	//看没有发送成功时添加的参数
	private IntentFilter sendFilter;
	private SendStatusReceiver sendStatusReceiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//接收信息
		sender = (TextView)findViewById(R.id.sender);
		content = (TextView)findViewById(R.id.content);
		intentFilter = new IntentFilter();
		intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
		intentFilter.setPriority(100);
		messageReceiver = new MessageReceiver();
		registerReceiver(messageReceiver, intentFilter);
		//发送信息
		to = (EditText)findViewById(R.id.to);
		msgInput=(EditText)findViewById(R.id.msg_input);
		button = (Button)findViewById(R.id.send);
		//看有没有发送成功
		sendFilter = new IntentFilter();
		sendFilter.addAction("SENT_SMS_ACTION");
		sendStatusReceiver = new SendStatusReceiver();
		registerReceiver(sendStatusReceiver, sendFilter);
		button.setOnClickListener(this);
		
	}
	
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(messageReceiver);
		unregisterReceiver(sendStatusReceiver);
	}



	class MessageReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			Object[] pdus = (Object[])bundle.get("pdus");//提取短信消息
			SmsMessage[] messages = new SmsMessage[pdus.length];
			for(int i=0;i<messages.length;i++){
				messages[i] =SmsMessage.createFromPdu((byte[])pdus[i]);
			}
			String address = messages[0].getOriginatingAddress();//获取发送方号码
			String funllMessage="";
			for(SmsMessage message:messages){
				funllMessage+=message.getMessageBody();//获取短信内容
			}
			sender.setText(address);
			content.setText(funllMessage);
			abortBroadcast();
		}
		
	}
	
	
	class SendStatusReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			if(getResultCode()==RESULT_OK){//短信发送成功
				Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
			}else{//发送失败
				Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
			}
		}
		
	}


	/**
	 * 点击发送按钮 发送消息出去
	 */
	@Override
	public void onClick(View v) {
		SmsManager smsManager = SmsManager.getDefault();
		Intent sentintent = new Intent("SENT_SMS_ACTION");
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, sentintent, 0);
		smsManager.sendTextMessage(to.getText().toString(), null, msgInput.getText().toString(), pi, null);
	}
}













