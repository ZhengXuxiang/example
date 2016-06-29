package com.example.lightsensortest;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {
	
	private SensorManager sensorManager;
	private TextView lightLevel;
	
	private SensorEventListener listener =new SensorEventListener() {
		@Override
		public void onSensorChanged(SensorEvent event) {
			//values 数组中第一个下标的值就是当前的光照强度
			float value  = event.values[0];
			lightLevel.setText("Current light level is"+value+"lx");
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};
	
	//光照传感器
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		lightLevel = (TextView)findViewById(R.id.lightLevel);
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		Sensor  sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		sensorManager.registerListener(listener, sensor,SensorManager.SENSOR_DELAY_NORMAL);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(sensorManager!=null){
			sensorManager.unregisterListener(listener);
		}
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
