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
	
	//��ȡ����
	private IntentFilter intentFilter;
	private MessageReceiver messageReceiver;
	
	//�������ʱ����ӵĲ���,���Ͱ�ť
	private EditText to;
	private EditText msgInput;
	private Button button;
	
	//��û�з��ͳɹ�ʱ��ӵĲ���
	private IntentFilter sendFilter;
	private SendStatusReceiver sendStatusReceiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//������Ϣ
		sender = (TextView)findViewById(R.id.sender);
		content = (TextView)findViewById(R.id.content);
		intentFilter = new IntentFilter();
		intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
		intentFilter.setPriority(100);
		messageReceiver = new MessageReceiver();
		registerReceiver(messageReceiver, intentFilter);
		//������Ϣ
		to = (EditText)findViewById(R.id.to);
		msgInput=(EditText)findViewById(R.id.msg_input);
		button = (Button)findViewById(R.id.send);
		//����û�з��ͳɹ�
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
			Object[] pdus = (Object[])bundle.get("pdus");//��ȡ������Ϣ
			SmsMessage[] messages = new SmsMessage[pdus.length];
			for(int i=0;i<messages.length;i++){
				messages[i] =SmsMessage.createFromPdu((byte[])pdus[i]);
			}
			String address = messages[0].getOriginatingAddress();//��ȡ���ͷ�����
			String funllMessage="";
			for(SmsMessage message:messages){
				funllMessage+=message.getMessageBody();//��ȡ��������
			}
			sender.setText(address);
			content.setText(funllMessage);
			abortBroadcast();
		}
		
	}
	
	
	class SendStatusReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			if(getResultCode()==RESULT_OK){//���ŷ��ͳɹ�
				Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
			}else{//����ʧ��
				Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
			}
		}
		
	}


	/**
	 * ������Ͱ�ť ������Ϣ��ȥ
	 */
	@Override
	public void onClick(View v) {
		SmsManager smsManager = SmsManager.getDefault();
		Intent sentintent = new Intent("SENT_SMS_ACTION");
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, sentintent, 0);
		smsManager.sendTextMessage(to.getText().toString(), null, msgInput.getText().toString(), pi, null);
	}
}













