package com.example.sharepreferencestest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button saveData;
	private Button getData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		saveData =(Button)findViewById(R.id.save_data);
		saveData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
				editor.putBoolean("flag", false);
				editor.putString("name", "tom");
				editor.putInt("age", 11);
				editor.commit();
			}
		});
		
		getData = (Button)findViewById(R.id.get_data);
		getData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences editor = getSharedPreferences("data", MODE_PRIVATE);
				String name = editor.getString("name","");
				int age = editor.getInt("age", 0);
				boolean flag = editor.getBoolean("flag", true);
				Toast.makeText(getBaseContext(), name+":"+age+":"+flag, Toast.LENGTH_SHORT).show();
			}
		});
	}
}















