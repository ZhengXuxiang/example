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
	 * conertView ������֮ǰ���غõĲ��ֽ��л���
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		Fruit fruit = getItem(position);//��ȡ��ǰ���Fruitʵ��
		View view ;
		ViewHolder viewHolder;
		if(convertView==null){
			view =  LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.fruitImage=(ImageView)view.findViewById(R.id.fruitImage);
			viewHolder.fruitName=(TextView)view.findViewById(R.id.fruitName);
			//���ڻ�����
			view.setTag(viewHolder);
		}else {
			view = convertView;
			//��View������ȥȡ
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




















