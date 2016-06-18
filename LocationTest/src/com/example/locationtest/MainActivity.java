package com.example.locationtest;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private TextView positionTest;
	private LocationManager locationManager;
	private String provider;
	
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

	private void showLocation(Location location) {
		String currentPosition="latitude is"+location.getLatitude()+"\n"+"longitude is"+location.getLongitude();
		positionTest.setText(currentPosition);
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
