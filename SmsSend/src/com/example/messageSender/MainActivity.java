package com.example.messageSender;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{

	private  EditText phoneNum;
	private  EditText msg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		phoneNum = (EditText)findViewById(R.id.et_phoneNum);
		 msg = (EditText)findViewById(R.id.messageContent);
		
		Button button = (Button)findViewById(R.id.sendButtons);
		button.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View view){
		String phone = phoneNum.getText().toString();
		String content = msg.getText().toString();
		
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(
				phone,//收件人
				null, //短信中心号码
				content, //内容
				null, 
				null);
	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
