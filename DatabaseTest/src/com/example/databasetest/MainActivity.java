package com.example.databasetest;

import android.R.integer;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private MyDatabaseHelper myDatabaseHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button)findViewById(R.id.button);
		//ʵ�������� �����1�����,onUpgrade�ͻ�ִ��
		myDatabaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				myDatabaseHelper.getWritableDatabase();//
			}
		});
		//�����������ݲ���
		Button addData=(Button)findViewById(R.id.addData);
		addData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				//��ʼ��װ��һ������
				values.put("name", "��");
				values.put("author","����");
				values.put("pages", 50);
				values.put("price", 20.43);
				db.insert("Book", null, values);
				values.clear();
				//��ʼ��װ�ڶ�������
				values.put("name", "���˹͡");
				values.put("author","�޲���");
				values.put("pages", 500);
				values.put("price", 18.43);
				db.insert("Book", null, values);
				values.clear();
			}
		});
		//��������
		Button updata = (Button)findViewById(R.id.updata);
		updata.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("name", "�̰�");
				db.update("Book", values, "author=?", new String[]{"����"});
				values.clear();
			}
		});
		//ɾ������
		Button delete = (Button)findViewById(R.id.delete);
		delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
				db.delete("Book", "pages>?", new String[]{"5"});
			}
		});
		
		//��ѯ����
		Button retrieve = (Button)findViewById(R.id.retrieve);
		retrieve.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
				//��ѯ������������
				Cursor cursor =db.query("Book", null,null,null,null,null,null);
				//if(cursor.moveToNext()){
					while(cursor.moveToNext()){
					String name = cursor.getString(cursor.getColumnIndex("name"));
					String author=cursor.getString(cursor.getColumnIndex("author"));
					float price = cursor.getFloat(cursor.getColumnIndex("price"));
					int pages = cursor.getInt(cursor.getColumnIndex("pages"));
					Log.d("MainActivity", name);
					Log.d("MainActivity", author);
					Log.d("MainActivity", price+"");
					Log.d("MainActivity", pages+"");
					Toast.makeText(getBaseContext(), name+":"+author+":"+price+":"+pages, Toast.LENGTH_SHORT).show();
					}
				//}
			}
		});
		
		//ɾ��������,��������
		Button replace = (Button)findViewById(R.id.replace);
		replace.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
				db.beginTransaction();//��������
				try {
					db.delete("Book", null, null);
					//if(true){
						//�����ֶ��׳�һ���쳣,������ʧ��
					//	throw new NullPointerException();
					//}
					ContentValues values = new ContentValues();
					values.put("name", "��");
					values.put("author","��1");
					values.put("pages", 50);
					values.put("price", 20.43);
					db.insert("Book", null, values);
					db.setTransactionSuccessful();//�����Ѿ�ִ�гɹ�
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					db.endTransaction();//��������
				}
			}
		});
	}
}









