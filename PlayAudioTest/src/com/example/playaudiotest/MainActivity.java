package com.example.playaudiotest;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	
	private Button start;
	private Button pause;
	private Button stop;
	
	private MediaPlayer media = new MediaPlayer();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获取按钮组件
		start = (Button)findViewById(R.id.start);
		pause= (Button)findViewById(R.id.pause);
		stop = (Button)findViewById(R.id.stop);
		
		start.setOnClickListener(this);
		pause.setOnClickListener(this);
		stop.setOnClickListener(this);
		initMediaPlay();//初始化Mediaplay
		
		
		
	}



	private void initMediaPlay() {
		File file = new File(Environment.getExternalStorageDirectory(),"music.mp3");
		try {
			media.setDataSource(file.getPath());//指定音频文件的路径
			media.prepare();//让mediapaly进入准备的状态
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start:
			if(!media.isPlaying()){
				media.start();//开始播放
			}
			break;
		case R.id.pause:
			if(media.isPlaying()){
				media.pause();
			}
					break;
		case R.id.stop:
			if (media.isPlaying()) {
				media.stop();
			}
			break;
		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (media!=null) {
			media.stop();
			media.release();
		}
	}

	

}




















