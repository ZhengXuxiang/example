package com.example.filepersistencetest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private EditText editText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editText = (EditText)findViewById(R.id.edit);
		String inputText = load();
		if(!TextUtils.isEmpty(inputText)){
			editText.setText(inputText);
			editText.setSelection(inputText.length());
			Toast.makeText(this, "Restorty", Toast.LENGTH_SHORT).show();
		}
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		String inputText =editText.getText().toString();
		save(inputText);
	}

	private void save(String inputText) {
		FileOutputStream out = null;
		BufferedWriter bw = null;
		try {
			out=openFileOutput("data", Context.MODE_PRIVATE);
			bw = new BufferedWriter(new OutputStreamWriter(out));
			bw.write(inputText);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bw!=null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public String load(){
		FileInputStream in = null;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			in = openFileInput("data");
			br = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while((line=br.readLine())!=null){
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
}
