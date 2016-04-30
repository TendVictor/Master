package com.example.pants.tooldemo.tool.tool_1.layout;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.example.pants.tooldemo.tool.tool_1.Activity_1;

public class ListPagerLayout extends ViewPager {

	Activity_1 mMain;

	int currentPage = 0;

	public ListPagerLayout(Context context) {
		super(context);

		mMain = (Activity_1) context;

		this.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	public class MyOnPageChangeListener extends SimpleOnPageChangeListener {
		// 可重写部分方法
		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixelsAuto) {
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}

		@Override
		public void onPageSelected(int position) {
			
		}
	}

	public void changePage(int position) {
		setCurrentItem(position);
	}

}
