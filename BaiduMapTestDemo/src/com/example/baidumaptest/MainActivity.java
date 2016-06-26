package com.example.baidumaptest;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapView;
import com.example.baidumaptest.R;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {
	private BMapManager manager;
	private MapView mapView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manager=new BMapManager(this);
		manager.init("hQXWnlFxflrg7ZuHUaxDOFrLZLFW7YPV", null);
		setContentView(R.layout.fragment_main);
		mapView=(MapView)findViewById(R.id.map_view);
		mapView.setBuiltInZoomControls(true);
	}
	
	@Override
	protected void onResume() {
		mapView.onResume();
		if(manager!=null){
			manager.start();
		}
		super.onResume();
	}

	@Override
	protected void onPause() {
		mapView.onPause();
		if(manager!=null){
			manager.stop();
		}
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		mapView.destroy();
		if(manager!=null){
			manager.destroy();
		}
		super.onDestroy();
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
