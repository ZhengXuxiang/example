package com.example.fragmenttest;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button =(Button)findViewById(R.id.button);
		button.setOnClickListener(this);
	}

	@Override 
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			AnotherFragment fragment = new AnotherFragment();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.right_layout, fragment);
			//给事物添加返回栈方法  使得点击返回按钮的时候不会立马退出程序,会返回上一次的碎片界面
			ft.addToBackStack(null);
			ft.commit();
			break;
		default:
			break;
		}
	}

	
}
