package com.example.accelerometersensortest;

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
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {
	
	private  SensorManager sensorManager;
	
	
	private SensorEventListener listener =new SensorEventListener() {
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			//加速度可能会是负值,所以要取他们的绝对值
			float xValue =Math.abs(event.values[0]);
			float yValue = Math.abs(event.values[1]);
			float zValue = Math.abs(event.values[2]);
			if(xValue>15||yValue>15||zValue>15){
				//认为用户摇动了手机,触发摇一摇逻辑
				Toast.makeText(MainActivity.this, "摇一摇", Toast.LENGTH_LONG).show();
			}
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			
		}
	};
	
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(sensorManager!=null){
			sensorManager.unregisterListener(listener);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		Sensor sensor= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(listener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
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
