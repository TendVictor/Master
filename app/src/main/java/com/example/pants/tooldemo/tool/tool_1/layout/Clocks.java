package com.example.pants.tooldemo.tool.tool_1.layout;

import android.content.Context;
import android.widget.RelativeLayout;

import com.example.pants.tooldemo.tool.tool_1.clock1.Clock1;
import com.example.pants.tooldemo.tool.tool_1.clock2.Clock2;
import com.example.pants.tooldemo.tool.tool_1.clock3.Clock3;
import com.example.pants.tooldemo.tool.tool_1.clock4.Clock4;

public class Clocks extends RelativeLayout {

	Context mContext;
	LayoutParams params;

	RelativeLayout currentClock = null;

	boolean[] deleteTag = {true, false, false, false};

	RelativeLayout[] clocks;
	public Clock1 clock1;
	public Clock2 clock2;
	public Clock3 clock3;
	public Clock4 clock4;

	public Clocks(Context context) {
		super(context);
		mContext = context;

		initialClockArray();
	}

	private void initialClockArray() {
		clocks = new RelativeLayout[4];
		clock1 = new Clock1(mContext);
		clock2 = new Clock2(mContext);
		clock3 = new Clock3(mContext);
		clock4 = new Clock4(mContext);
		clocks[0] = clock1;
		clocks[1] = clock2;
		clocks[2] = clock3;
		clocks[3] = clock4;

		params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);

		// 初始动画
		currentClock = clocks[0];
		addView(currentClock, params);
		doFadeInAnimation(0);
	}

	public void changeClock(int currentPage, int nextPage) {
		updateDeleteTag(currentPage, nextPage);
		updateClock(currentPage, nextPage);

		doFadeInAnimation(nextPage);
	}

	private void updateDeleteTag(int currentPage, int nextPage) {
		deleteTag[currentPage] = false;
		deleteTag[nextPage] = true;
	}

	private void updateClock(int positionFrom, int positionTo) {
		removeView(clocks[positionFrom]);

		currentClock = clocks[positionTo];
		addView(currentClock, params);
	}


	private void doFadeInAnimation(int nextPage) {
		switch (nextPage) {
		case 0:
			((Clock1) currentClock).doFadeInAnimation();
			break;
		case 1:
			((Clock2) currentClock).doFadeInAnimation();
			break;
		case 2:
			((Clock3) currentClock).doFadeInAnimation();
			break;
		case 3:
			((Clock4) currentClock).doFadeInAnimation();
			break;
		}
	}

}
