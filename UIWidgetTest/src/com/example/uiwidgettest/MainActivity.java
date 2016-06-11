package com.example.uiwidgettest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	
	private Button button;
	
	private EditText editText;
	
	private ImageView imageView;
	
	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button =(Button)findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.button:
//					TextView e = (TextView)findViewById(R.id.text_view);
					//e.setTextColor(100);
//					e.setTextColor(100);
//					Intent intent = new Intent(MainActivity.this,SecondActivity.class);
//					startActivity(intent);
					//文本框
//					editText = (EditText)findViewById(R.id.editText);
//					String inputText = editText.getText().toString();
//					Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
					/*
					 //图片
					imageView =(ImageView)findViewById(R.id.image);
					Log.d("MainActivity", imageView.getResources().getResourceEntryName(R.id.image));
					Log.d("MainActivity", imageView.getResources().getResourceTypeName(R.id.image));
					Log.d("MainActivity", imageView.getResources().getResourcePackageName(R.id.image));
					if(imageView.getResources().getResourceEntryName(R.id.image)=="ic_cat"){
						imageView.setImageResource(R.drawable.ic_launcher);
					}else{
						imageView.setImageResource(R.drawable.ic_cat);
					}
					//进度条
					progressBar = (ProgressBar)findViewById(R.id.progress);
					if(progressBar.getVisibility()==View.GONE){
						int length = progressBar.getProgress();
						length+=10;
						progressBar.setProgress(length);
						progressBar.setVisibility(View.VISIBLE);
						
					}else{
						progressBar.setVisibility(View.GONE);
					}
					*/
					//对话框
					/*
					AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
					dialog.setTitle("this is a dialog");
					dialog.setMessage("Something importing");
					dialog.setCancelable(false);
					dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
					});
					dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
					});
					dialog.show();
					*/
					
					//带进度条的对话框
					ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
					progressDialog.setTitle("111");
					progressDialog.setMessage("dasdasd");
					//如果设置为false那么返回键不能取消掉,数据加载完必须调用ProgressDialog的dismiss();
					progressDialog.setCancelable(true);
					progressDialog.show();
					break;
				default:
					break;
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
}
