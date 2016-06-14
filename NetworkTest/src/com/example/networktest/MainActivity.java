package com.example.networktest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputBinding;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	
	public static final int SHOW_RESPONSE=0;
	private Button sendRequest;
	private TextView responseText;
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_RESPONSE:
				String response = (String)msg.obj;
				//在这里进行UI操作,将结果显示到界面上
				responseText.setText(response);
			}
		}
		
	};
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sendRequest = (Button)findViewById(R.id.sendRequest);
		responseText = (TextView)findViewById(R.id.responseText);
		sendRequest.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.sendRequest){
			sendRequestWithHttpURLConnection();
		}
	}
	

	private void sendRequestWithHttpURLConnection() {
		//开启线程来发起网络请求 HttpClient 对象的实现方式
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					HttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet=new HttpGet("http://www.baidu.com");
					HttpResponse response = httpClient.execute(httpGet);
					if(response.getStatusLine().getStatusCode()==200){
						//请求响应成功了
						HttpEntity entity = response.getEntity();
						String resText = EntityUtils.toString(entity,"UTF-8");
						Message msg = new Message();
						msg.obj=resText;
						msg.what=SHOW_RESPONSE;
						handler.sendMessage(msg);
							
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		
		//开启线程来发起网络请求 HttpURLConnection 对象的实现方式
	/*	new Thread(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
					URL url = new URL("http://www.baidu.com");
					connection = (HttpURLConnection)url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					InputStream in = connection.getInputStream();
					//下面对获取的输入信息进行读取
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder sb = new StringBuilder();
					String line;
					while((line=reader.readLine())!=null){
						sb.append(line);
					}
					Message msg = new Message();
					msg.what = SHOW_RESPONSE;
					msg.obj=sb.toString();
					handler.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(connection!=null){
						connection.disconnect();
					}
				}
			}
		}).start();
		*/
	}
}






