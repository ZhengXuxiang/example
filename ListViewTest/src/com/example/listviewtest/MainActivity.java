package com.example.listviewtest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String[] data={"Apple","Banana","Orange","Waterelom","Pear","Grape"
			,"Pineapple","Strawberry","Cherry","Mango"};
	
	private List<Fruit> fruitList = new ArrayList<Fruit>();	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initFrut();//初始化水果数据
		//创建适配器对象
		FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
//		ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,data);
		ListView listView = (ListView)findViewById(R.id.listView);
		listView.setAdapter(adapter);
		
		//ListView的点击事件
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Fruit fruit = fruitList.get(position);
				Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
				
			}
		});
		
		
		
	}
	private void initFrut() {
		/*Fruit apple = new Fruit("apple", R.drawable.apple);
		fruitList.add(apple);*/
		Fruit Banana = new Fruit("Banana", R.drawable.shanzhu);
		fruitList.add(Banana);
		Fruit Orange = new Fruit("Orange", R.drawable.orange);
		fruitList.add(Orange);
		Fruit Waterelom = new Fruit("Waterelom", R.drawable.watermelon);
		fruitList.add(Waterelom);
		/*Fruit Pear = new Fruit("Pear", R.drawable.pear);
		fruitList.add(Pear);*/
		Fruit Grape = new Fruit("Grape", R.drawable.pitaya);
		fruitList.add(Grape);
		Fruit Pineapple = new Fruit("Pineapple", R.drawable.pineapple);
		fruitList.add(Pineapple);
		Fruit Strawberry = new Fruit("Strawberry", R.drawable.strawberry);
		fruitList.add(Strawberry);
		Fruit Cherry = new Fruit("Cherry", R.drawable.lemon);
		fruitList.add(Cherry);
		Fruit Mango = new Fruit("Mango", R.drawable.cucumber);
		fruitList.add(Mango);
	}


}
