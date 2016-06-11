package com.example.fragmentbestpractice;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NewsTitleFragment extends Fragment implements OnItemClickListener {
	private ListView newsTitleListView;
	private List<News> newsList;
	private NewsAdapter adapter;
	private boolean isTwoPane;

	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		newsList = getNews();//初始化新闻数据
		adapter = new NewsAdapter(activity, R.layout.news_item, newsList);
		
	}
	
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.news_title_frag, container,false);
		newsTitleListView =(ListView)view.findViewById(R.id.news_title_list_view);
		newsTitleListView.setAdapter(adapter);
		newsTitleListView.setOnItemClickListener(this);
		return view;
	}
	
	




	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if(getActivity().findViewById(R.id.news_content)!=null){
			isTwoPane = true;
		}else {
			isTwoPane = false;
		}
	}

	


	private List<News> getNews(){
		List<News> newsList = new ArrayList<News>();
		News news1 =new News();
		news1.setTitle("标题1");
		news1.setContent("内容1");
		newsList.add(news1);
		News news2 = new News();
		news2.setTitle("标题2发送到范德萨方式非矿大厦付款hd声卡粉红色的花咖啡好看撒的付款哈sd卡哈福克斯的飞速达");
		news2.setContent("news荣3范德萨否决了束带结发卢卡斯的加法乱收费的福建所带来三打两建付了款束带结发了师范军绿色的否决了的思路福师大飞否决了松岛枫了几声费违法加");
		newsList.add(news2);
		return newsList;
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		News news = newsList.get(position);
		if(isTwoPane){
			//双页模式
			NewsContentFragment newsContentFragment=(NewsContentFragment)getFragmentManager().findFragmentById(R.id.news_content_fragment);
			newsContentFragment.refresh(news.getTitle(), news.getContent());
		}else {
			NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
		}

	}

}
