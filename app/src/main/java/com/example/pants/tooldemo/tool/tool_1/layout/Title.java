package com.example.pants.tooldemo.tool.tool_1.layout;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pants.tooldemo.tool.tool_1.Activity_1;

public class Title extends LinearLayout {

	final int paddingLeftOut = 20, paddingRightOut = 20;
	final int paddingTop = 6, paddingBottom = 8;
	final int paddingCenter = 36;
	final int textSize = 16;

	final int colorLight = 0xffffffff, colorGray = 0x99ffffff;

	TextView tv0, tv1, tv2, tv3;
	TextView[] textViews;

	Context mContext;
	Activity_1 mMain;

	public Title(Context context) {
		super(context);
		mContext = context;
		mMain = (Activity_1) context;

		tv0 = new TextView(context);
		tv1 = new TextView(context);
		tv2 = new TextView(context);
		tv3 = new TextView(context);

		textViews = new TextView[4];
		textViews[0] = tv0;
		textViews[1] = tv1;
		textViews[2] = tv2;
		textViews[3] = tv3;

		tv0.setText("闹钟");
		tv1.setText("时钟");
		tv2.setText("秒表");
		tv3.setText("计时器");

		tv0.setTextSize(textSize);
		tv1.setTextSize(textSize);
		tv2.setTextSize(textSize);
		tv3.setTextSize(textSize);
		tv0.setTextColor(colorLight);
		tv1.setTextColor(colorGray);
		tv2.setTextColor(colorGray);
		tv3.setTextColor(colorGray);

		tv0.setPadding(paddingCenter, paddingTop, paddingCenter, paddingBottom);
		tv1.setPadding(paddingCenter, paddingTop, paddingCenter, paddingBottom);
		tv2.setPadding(paddingCenter, paddingTop, paddingCenter, paddingBottom);
		tv3.setPadding(paddingCenter, paddingTop, paddingCenter, paddingBottom);

		setPadding(paddingLeftOut, 0, paddingRightOut, 0);

		addView(tv0);
		addView(tv1);
		addView(tv2);
		addView(tv3);

		TitleOnClickListener listener = new TitleOnClickListener();
		tv0.setTag(0);
		tv1.setTag(1);
		tv2.setTag(2);
		tv3.setTag(3);
		tv0.setOnClickListener(listener);
		tv1.setOnClickListener(listener);
		tv2.setOnClickListener(listener);
		tv3.setOnClickListener(listener);
	}

	public void changePage(int currentPage, int position) {
		textViews[position].setTextColor(colorLight);
		textViews[currentPage].setTextColor(colorGray);
	}

	class TitleOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			int position = (Integer) v.getTag();

			if (Activity_1.currentPage != position) {
				mMain.changePage(position);
			}
		}
	}

}
