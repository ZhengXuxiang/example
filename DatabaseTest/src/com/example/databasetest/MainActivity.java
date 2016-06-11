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
		//实例化对象 传入比1大的数,onUpgrade就会执行
		myDatabaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				myDatabaseHelper.getWritableDatabase();//
			}
		});
		//向表中添加数据操作
		Button addData=(Button)findViewById(R.id.addData);
		addData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				//开始组装第一条数据
				values.put("name", "简爱");
				values.put("author","大卫");
				values.put("pages", 50);
				values.put("price", 20.43);
				db.insert("Book", null, values);
				values.clear();
				//开始组装第二条数据
				values.put("name", "简奥斯汀");
				values.put("author","罗伯特");
				values.put("pages", 500);
				values.put("price", 18.43);
				db.insert("Book", null, values);
				values.clear();
			}
		});
		//更新数据
		Button updata = (Button)findViewById(R.id.updata);
		updata.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("name", "奶爸");
				db.update("Book", values, "author=?", new String[]{"大卫"});
				values.clear();
			}
		});
		//删除数据
		Button delete = (Button)findViewById(R.id.delete);
		delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
				db.delete("Book", "pages>?", new String[]{"5"});
			}
		});
		
		//查询数据
		Button retrieve = (Button)findViewById(R.id.retrieve);
		retrieve.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
				//查询表中所有数据
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
		
		//删除旧数据,换新数据
		Button replace = (Button)findViewById(R.id.replace);
		replace.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
				db.beginTransaction();//开启事务
				try {
					db.delete("Book", null, null);
					//if(true){
						//这里手动抛出一个异常,让事务失败
					//	throw new NullPointerException();
					//}
					ContentValues values = new ContentValues();
					values.put("name", "李");
					values.put("author","李1");
					values.put("pages", 50);
					values.put("price", 20.43);
					db.insert("Book", null, values);
					db.setTransactionSuccessful();//事务已经执行成功
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					db.endTransaction();//结束事务
				}
			}
		});
	}
}









