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
 *程序刚运行就显示的界面
 */
public class MainUI extends Activity {
	/**
	 * 界面被创建的时候调用此方法
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);//必须执行此代码
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
	 * 当拨打此号码按钮被点击的时候出发方法
	 * @param v
	 */
	public void call(View v){
		//取数字
		EditText eText =(EditText)findViewById(R.id.number);
		String number = eText.getText().toString();
		
		//拨打号码
		Intent intent = new Intent();
		//指定拨号动作
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:"+number));
		startActivity(intent);
		Log.d("data", "拨号按钮被触发了");
	}
}
























