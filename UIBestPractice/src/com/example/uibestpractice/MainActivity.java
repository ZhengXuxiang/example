package com.example.uibestpractice;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private ListView msgListView;
	private EditText inputText;
	private Button button;
	private MsgAdapter adapter;
	private List<Msg> msgList=new ArrayList<Msg>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		//初始化信息
		initMsg();
		adapter = new MsgAdapter(MainActivity.this, 0, R.layout.msg_item, msgList);
		inputText=(EditText)findViewById(R.id.inputText);
		button = (Button)findViewById(R.id.send);
		msgListView=(ListView)findViewById(R.id.msgListView);
		msgListView.setAdapter(adapter);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String content = inputText.getText().toString();
				if(!"".equals(content)){
					Msg msg = new Msg(content, Msg.TYPE_SEND);
					msgList.add(msg);
					//当有新消息的时候刷新ListView中的显示
					adapter.notifyDataSetChanged();
					msgListView.setSelection(msgList.size());//将ListView定位到最后一行
					inputText.setText("");//清空输入框中的消息
				}
			}
		});
		
		
		
		
	}

	private void initMsg() {
		Msg msg1 =new Msg("recevidfdsfsdfsdfhdskfhksdhfkdsfsdkfhksed1", Msg.TYPE_RECEIVED);
		Msg msg2 = new Msg("send1", Msg.TYPE_SEND);
		Msg msg3 =new Msg("recevied2", Msg.TYPE_RECEIVED);
		msgList.add(msg1);
		msgList.add(msg2);
		msgList.add(msg3);
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
