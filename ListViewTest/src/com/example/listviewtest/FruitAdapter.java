package com.example.listviewtest;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FruitAdapter extends ArrayAdapter<Fruit> {
	
	private int resourceId;
	
	public FruitAdapter(Context context, int textViewResourceId,List<Fruit> objects) {
		super(context,textViewResourceId,objects);
		resourceId=textViewResourceId;
	}

	@Override
	/**
	 * conertView 是用于之前加载好的布局进行缓存
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		Fruit fruit = getItem(position);//获取当前项的Fruit实例
		View view ;
		ViewHolder viewHolder;
		if(convertView==null){
			view =  LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.fruitImage=(ImageView)view.findViewById(R.id.fruitImage);
			viewHolder.fruitName=(TextView)view.findViewById(R.id.fruitName);
			//放在缓存中
			view.setTag(viewHolder);
		}else {
			view = convertView;
			//从View缓存中去取
			viewHolder=(ViewHolder)view.getTag();
		}
		viewHolder.fruitImage.setImageResource(fruit.getImageId());
		viewHolder.fruitName.setText(fruit.getName());
		return view;
	}
	
	class ViewHolder{
		ImageView fruitImage;
		TextView fruitName;
	}
}




















