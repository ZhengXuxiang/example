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
			//���ٶȿ��ܻ��Ǹ�ֵ,����Ҫȡ���ǵľ���ֵ
			float xValue =Math.abs(event.values[0]);
			float yValue = Math.abs(event.values[1]);
			float zValue = Math.abs(event.values[2]);
			if(xValue>15||yValue>15||zValue>15){
				//��Ϊ�û�ҡ�����ֻ�,����ҡһҡ�߼�
				Toast.makeText(MainActivity.this, "ҡһҡ", Toast.LENGTH_LONG).show();
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

	}


}
