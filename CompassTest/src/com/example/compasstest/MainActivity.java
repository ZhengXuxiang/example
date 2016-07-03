package com.example.compasstest;

import javax.security.auth.PrivateCredentialPermission;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.os.Build;

public class MainActivity extends Activity {
	
	private SensorManager sensorManager;
	
	private ImageView compassImg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		compassImg =(ImageView)findViewById(R.id.compass_img);
		
		sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
		Sensor magneticSensor =sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(listener, magneticSensor,SensorManager.SENSOR_DELAY_GAME);
		sensorManager.registerListener(listener, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(sensorManager!=null){
			sensorManager.unregisterListener(listener);
		}
	}
	
	
	private SensorEventListener listener=new SensorEventListener() {
		
		float[] acclerometerValues= new float[3];
		float[] magneticValues =new float[3];
		
		private float lastRotateDegerr;
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			//判断当前是加速传感器还是地磁传感器
			if(event.sensor.getType()== Sensor.TYPE_ACCELEROMETER){
				//注意赋值的时候要调用clone()方法
				acclerometerValues = event.values.clone();
			}else if (event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD) {
				magneticValues = event.values.clone();
			}
			
			float[] R = new float[9];
			float[] values = new float[3];
			SensorManager.getRotationMatrix(R, null, acclerometerValues, magneticValues);
			SensorManager.getOrientation(R, values);
			Log.d("MainActivity", "value[0] is"+Math.toDegrees(values[0]));
			
			//将计算出的旋转角度取反,用于旋转指南针背景图片
			float rotateDegree = -(float)Math.toDegrees(values[0]);
			if(Math.abs(rotateDegree-lastRotateDegerr)>1){
				RotateAnimation animation = new RotateAnimation(lastRotateDegerr, rotateDegree,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
				animation.setFillAfter(true);
				compassImg.startAnimation(animation);
				lastRotateDegerr=rotateDegree;
			}
			
			
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			
		}
	};
	
}
