package com.example.uibestpractice;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MsgAdapter extends ArrayAdapter<Msg> {
	
	private int resourceId;
	public MsgAdapter(Context context, int resource, int textViewResourceId,
			List<Msg> objects) {
		super(context, resource, textViewResourceId, objects);
		resourceId=textViewResourceId;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Msg msg = getItem(position);
		View view;
		ViewHolder viewHolder;
		if(convertView==null){
			view=LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.leftLayout=(LinearLayout)view.findViewById(R.id.leftLayout);
			viewHolder.rightLayout=(LinearLayout)view.findViewById(R.id.RightLayout);
			viewHolder.leftMsg=(TextView)view.findViewById(R.id.msgLeft);
			viewHolder.rightMsg=(TextView)view.findViewById(R.id.msgRight);
			view.setTag(viewHolder);
		}else{
			view = convertView;
			viewHolder=(ViewHolder)view.getTag();
		}
		
		if(msg.getType()==Msg.TYPE_RECEIVED){
			//��Ϣ�Ǳ��˷��ͽ�����,��Ϣ�������ʾ,�ұ߲�������
			viewHolder.leftLayout.setVisibility(View.VISIBLE);
			viewHolder.rightLayout.setVisibility(View.GONE);
			viewHolder.leftMsg.setText(msg.getContend());
		}else{
			//��Ϣ���Լ����ͳ�ȥ��,��Ϣ���ұ���ʾ,��ߵ�����,���Ҳ�ռλ��
			viewHolder.leftLayout.setVisibility(View.GONE);
			viewHolder.rightLayout.setVisibility(View.VISIBLE);
			viewHolder.rightMsg.setText(msg.getContend());
		}
		return view;
	}
	
	class ViewHolder{
		LinearLayout leftLayout;
		LinearLayout rightLayout;
		TextView leftMsg;
		TextView rightMsg;
	}
	

}
