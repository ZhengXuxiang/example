package com.example.locationtest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private TextView positionTest;
	private LocationManager locationManager;
	private String provider;
	
	public static final int SHOW_LOCATION=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		positionTest = (TextView)findViewById(R.id.positionTest);
		locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
		//获取当前所有可用的位置提供器
		List<String> providerList = locationManager.getProviders(true);
		if(providerList.contains(LocationManager.GPS_PROVIDER)){
			provider=LocationManager.GPS_PROVIDER;
		}else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
			provider=LocationManager.NETWORK_PROVIDER;
		}else{
			//当没有可用的位置提供器时,弹出Toast提示用户
			Toast.makeText(this, "No location provider to use",Toast.LENGTH_SHORT).show();
			return ;
		}
		Location location = locationManager.getLastKnownLocation(provider);
		if(location!=null){
			//显示当前设备的位置信息
			showLocation(location);
		}
		locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);
	}
	
	LocationListener locationListener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location location) {
			showLocation(location);
		}
	};

	private void showLocation(final Location location) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					//组装反向地理编码的接口地址
					StringBuilder url = new StringBuilder();
					url.append("http://maps.googleapis.com/maps/api/geocode/json?latlng=");
					url.append(location.getLatitude()).append(",");
					url.append(location.getLongitude());
					url.append("&sensor=false");
					
					URL uri = new URL(url.toString());
					HttpURLConnection connection = (HttpURLConnection)uri.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(200000);
					connection.setReadTimeout(200000);
					InputStream is = connection.getInputStream();
					//下面对获取到的流进行解读
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					StringBuilder res= new StringBuilder();
					String line;
					while((line=br.readLine())!=null){
						res.append(line);
					}
					JSONObject jsonObject = new JSONObject(res.toString());
					JSONArray jsonArray = jsonObject.getJSONArray("results");
					if(jsonArray.length()>0){
						JSONObject subObject = jsonArray.getJSONObject(0);
						String address = subObject.getString("formatted_address");
						Message message = new Message();
						message.what=SHOW_LOCATION;
						message.obj=address;
						handler.sendMessage(message);
					}
					
					
					
					/*
					HttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet(url.toString());
					//在消息请求头中指定语言,保证服务器会返回中文数据
					httpGet.addHeader("Accept-Language","zh-CN");
					HttpResponse response = httpClient.execute(httpGet);
					*/
					
					/*
					if(response.getStatusLine().getStatusCode()==200){
						HttpEntity entity=response.getEntity();
						String resp=EntityUtils.toString(entity,"utf-8");
						JSONObject jsonObject = new JSONObject(resp);
						//获取results节点下的位置信息
						JSONArray resultArray = jsonObject.getJSONArray("results");
						if(resultArray.length()>0){
							JSONObject subObject=resultArray.getJSONObject(0);
							//取出格式化的位置信息
							String address = subObject.getString("formatted_address");
							Message message = new Message();
							message.what=SHOW_LOCATION;
							message.obj=address;
							handler.sendMessage(message);
						}
					}*/
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		
		
//		String currentPosition="latitude is"+location.getLatitude()+"\n"+"longitude is"+location.getLongitude();
//		positionTest.setText(currentPosition);
	}
	
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_LOCATION:
				String currentPosition = (String)msg.obj;
				positionTest.setText(currentPosition);
				break;
			default:
				break;
			}
		}
		
	};

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
