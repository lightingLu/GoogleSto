package com.example.googlesto.fragment;

import java.util.List;

import com.example.googlesto.R;
import com.example.googlesto.adapter.DefaultAdapter;
import com.example.googlesto.adapter.ListBaseAdapter;
import com.example.googlesto.domin.AppInfo;
import com.example.googlesto.globle.GlobalContants;
import com.example.googlesto.holder.BaseHoder;
import com.example.googlesto.holder.ListBaseHolder;
import com.example.googlesto.protocol.AppProtocol;
import com.example.googlesto.protocol.HomeProtocol;
import com.example.googlesto.tools.UIUtils;
import com.example.googlesto.view.BaseListView;
import com.example.googlesto.view.LoadingPage.LoadResult;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class AppFragment extends BaseFragment {
	private List<AppInfo> datas;

	@Override
	public View createSuccessView() {
		BaseListView listview = new BaseListView(getContext());
		bitmapUtils = new BitmapUtils(getContext());
		listview.setAdapter(new ListBaseAdapter(datas,  listview){

			@Override
			public List<AppInfo> onload() {
				AppProtocol hProtocol = new AppProtocol();
				List<AppInfo> load = hProtocol.load(datas.size());
				datas.addAll(load);
				return load;
			}
			
		});
		listview.setOnScrollListener(new PauseOnScrollListener(bitmapUtils,
				false, true));
		bitmapUtils.configDefaultLoadingImage(R.drawable.ic_default); // �������ͼƬ��������ʾ��ͼƬ
		bitmapUtils.configDefaultLoadFailedImage(R.drawable.ic_default);// ����ʧ����ʾ��ͼƬ
		return listview;
		
	}

	
	@Override
	public LoadResult load() {
		AppProtocol hProtocol = new AppProtocol();
		datas = hProtocol.load(0);
		return checkData(datas);
	}

}
