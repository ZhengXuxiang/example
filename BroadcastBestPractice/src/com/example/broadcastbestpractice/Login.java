package com.example.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends BaseActivity {
	private EditText passwordText;
	private EditText accountText;
	private Button button;
	
	
	private SharedPreferences.Editor editor;
	private SharedPreferences sharePre;
	private CheckBox checkBox;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		accountText = (EditText)findViewById(R.id.account);
		passwordText = (EditText)findViewById(R.id.password);
		button = (Button)findViewById(R.id.login);
		//��ѡ��
		checkBox = (CheckBox)findViewById(R.id.remember_pass);
		//�־û�����-��ȡ
		sharePre = PreferenceManager.getDefaultSharedPreferences(this);
		//�Ƿ񱣴���Ʒ���
		boolean isRemember = sharePre.getBoolean("isRemember", false);
		if(isRemember){
			String account = sharePre.getString("account", "");
			String password = sharePre.getString("password", "");
			accountText.setText(account);
			passwordText.setText(password);
			checkBox.setChecked(true);
		}
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String account = accountText.getText().toString();
				String password = passwordText.getText().toString();
				if(account.equals("admin")&&password.equals("123456")){
					editor=sharePre.edit();
					if(checkBox.isChecked()){//��鸴ѡ���Ƿ�ѡ��
						editor.putBoolean("isRemember", true);
						editor.putString("account", account);
						editor.putString("password", password);
					}else{
						editor.clear();
					}
					editor.commit();
					Intent intent = new Intent(Login.this,MainActivity.class);
					startActivity(intent);
					finish();
				}else{
					Toast.makeText(Login.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	
	
}
