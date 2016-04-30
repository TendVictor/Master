package com.example.pants.tooldemo.tool.tool_1.layout;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;

public class DisplayLayout extends LinearLayout {

	public Title title;
	public Clocks clock;

	int[] colors = new int[] {0xff008171, 0xff237fae, 0xff3694a9, 0xff728d98};

	public DisplayLayout(Context context) {
		super(context);
		setOrientation(LinearLayout.VERTICAL);
		setGravity(Gravity.CENTER_HORIZONTAL);
		setPadding(10, 20, 10, 40);
		setBackgroundColor(colors[0]);

		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(20, 20, 20, 20);
		LayoutParams params2 = new LayoutParams(480, 480);

		title = new Title(context);
		addView(title, params);

		clock = new Clocks(context);
		addView(clock, params2);
	}

	public void changeDisplay(int currentPage, int nextPage) {
		setBackgroundColor(colors[nextPage]);

		title.changePage(currentPage, nextPage);
		clock.changeClock(currentPage, nextPage);
	}

}
