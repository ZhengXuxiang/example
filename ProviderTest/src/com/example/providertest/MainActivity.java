package com.example.providertest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private String newId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button addData= (Button)findViewById(R.id.addData);
		Button queryData = (Button)findViewById(R.id.queryData);
		Button updateData = (Button)findViewById(R.id.updateData);
		Button deleteData = (Button)findViewById(R.id.deleteData);
		
		//������ݰ�ť�����¼�
		addData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri uri= Uri.parse("content://com.example.databasetest.provider/book");
				ContentValues values = new ContentValues();
				values.put("name", "֣1��");
				values.put("author", "֣����");
				values.put("pages", 100);
				values.put("price", 10.29);
				Uri newUri = getContentResolver().insert(uri, values);
				newId = newUri.getPathSegments().get(1);
			}
		});
		
		//��ѯ���ݰ�ť�����¼�
		queryData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri uri= Uri.parse("content://com.example.databasetest.provider/book");
				Cursor cursor = getContentResolver().query(uri, null, null, null, null);
				if(cursor!=null){
					while(cursor.moveToNext()){
						String name = cursor.getString(cursor.getColumnIndex("name"));
						String author = cursor.getString(cursor.getColumnIndex("author"));
						int pages = cursor.getInt(cursor.getColumnIndex("pages"));
						float price = cursor.getFloat(cursor.getColumnIndex("price"));
						Log.d("MainActivity", name+":"+author+":"+pages+":"+price);
					}
				}
				cursor.close();
			}
		});
		//�������ݰ�ť�����¼�
		updateData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("content://com.example.databasetest.provider/book/"+newId);
				ContentValues values = new ContentValues();
				values.put("name", "֣2��");
				values.put("author", "֣����");
				values.put("pages", 1000);
				values.put("price", 100.29);
				getContentResolver().update(uri, values, null, null);
			}
		});			
		//ɾ�����ݰ�ť�����¼�
		deleteData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri uri= Uri.parse("content://com.example.databasetest.provider/book/"+newId);
				getContentResolver().delete(uri, null, null);
				
			}
		});
	}
}
