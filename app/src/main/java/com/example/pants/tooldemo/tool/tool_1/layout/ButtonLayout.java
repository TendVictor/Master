package com.example.pants.tooldemo.tool.tool_1.layout;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.pants.tooldemo.tool.tool_1.buttongroup.ButtonGroup1;
import com.example.pants.tooldemo.tool.tool_1.buttongroup.ButtonGroup2;
import com.example.pants.tooldemo.tool.tool_1.buttongroup.ButtonGroup3;
import com.example.pants.tooldemo.tool.tool_1.buttongroup.ButtonGroup4;

public class ButtonLayout extends LinearLayout {

	Context context;
	Handler handler = new Handler();
	LinearLayout[] btnGroups = new LinearLayout[4];

	public ButtonLayout(Context context) {
		super(context);
		this.context = context;
		setOrientation(LinearLayout.HORIZONTAL);
		setGravity(Gravity.CENTER);

		initialButtons();
	}

	private void initialButtons() {
		ButtonGroup1 btnGroup1 = new ButtonGroup1(context);
		ButtonGroup2 btnGroup2 = new ButtonGroup2(context);
		ButtonGroup3 btnGroup3 = new ButtonGroup3(context);
		ButtonGroup4 btnGroup4 = new ButtonGroup4(context);

		btnGroups[0] = btnGroup1;
		btnGroups[1] = btnGroup2;
		btnGroups[2] = btnGroup3;
		btnGroups[3] = btnGroup4;

		for (View view : btnGroups) {
			addView(view);
			view.setVisibility(LinearLayout.GONE);
		}

		btnGroups[0].setVisibility(LinearLayout.VISIBLE);
	}

	public void changePage(final int currentPage, final int nextPage) {
		
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				changeVisibility(currentPage);
			}

		}, 50);

		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				changeVisibility(nextPage);
			}
		}, 400);
	}

	private void changeVisibility(int currentPage) {
		if (btnGroups[currentPage].isShown())
			btnGroups[currentPage].setVisibility(LinearLayout.GONE);
		else
			btnGroups[currentPage].setVisibility(LinearLayout.VISIBLE);
	}
}
