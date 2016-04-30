package com.example.pants.tooldemo.tool.tool_1.buttongroup;

import android.content.Context;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pants.tooldemo.R;

public class ButtonGroup1 extends LinearLayout {

	Context context;

	LayoutParams params = new LayoutParams(66, 66);
	LayoutParams mParams = new LayoutParams(100, LayoutParams.MATCH_PARENT);

	public ButtonGroup1(Context context) {
		super(context);
		this.context = context;

		addButton_Add();
		addButton_Option();
	}

	private void addButton_Add() {
		LinearLayout layout1 = new LinearLayout(context);
		layout1.setOrientation(LinearLayout.VERTICAL);
		layout1.setGravity(Gravity.CENTER_HORIZONTAL);

		Button btnAdd = new Button(context);
		btnAdd.setBackgroundResource(R.drawable.btn_selector_add);
		layout1.addView(btnAdd, params);

		TextView tv = new TextView(context);
		tv.setText("添加闹钟");
		tv.setTextSize(12);
		tv.setTextColor(0xff777777);
		layout1.addView(tv);

		mParams.leftMargin = 0;
		mParams.rightMargin = 30;
		addView(layout1, mParams);
	}

	private void addButton_Option() {
		LinearLayout layout2 = new LinearLayout(context);
		layout2.setOrientation(LinearLayout.VERTICAL);
		layout2.setGravity(Gravity.CENTER_VERTICAL);

		Button btnOption = new Button(context);
		btnOption.setBackgroundResource(R.drawable.btn_selector_option);
		layout2.addView(btnOption, params);

		TextView tv = new TextView(context);
		tv.setText(" 设置");
		tv.setTextSize(12);
		tv.setTextColor(0xff777777);
		layout2.addView(tv);

		mParams.leftMargin = 30;
		mParams.rightMargin = 0;
		addView(layout2, mParams);
	}

}
