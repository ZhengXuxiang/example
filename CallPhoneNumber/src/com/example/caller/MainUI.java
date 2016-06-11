package com.example.caller;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
/**
 * 
 * @author sky
 *��������о���ʾ�Ľ���
 */
public class MainUI extends Activity {
	/**
	 * ���汻������ʱ����ô˷���
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);//����ִ�д˴���
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_ui, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * ������˺��밴ť�������ʱ���������
	 * @param v
	 */
	public void call(View v){
		//ȡ����
		EditText eText =(EditText)findViewById(R.id.number);
		String number = eText.getText().toString();
		
		//�������
		Intent intent = new Intent();
		//ָ�����Ŷ���
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:"+number));
		startActivity(intent);
		Log.d("data", "���Ű�ť��������");
	}
}
























