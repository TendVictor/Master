package com.example.pants.tooldemo.tool.tool_1.buttongroup;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.example.pants.tooldemo.R;

public class ButtonGroup3 extends LinearLayout {

	Context context;

	Button btnStart;
	LinearLayout btnRunning;

	public ButtonGroup3(Context context) {
		super(context);
		this.context = context;

		prepareUIState1();
		prepareUIState2();

		addView(btnRunning);
	}

	private void prepareUIState1() {
		btnStart = new Button(context);
		btnStart.setBackgroundResource(R.drawable.btn_singleline);
		btnStart.setText("开始");
		btnStart.setTextColor(0xff777777);
	}

	private void prepareUIState2() {
		LayoutParams params3 = new LayoutParams(324, 84);
		btnRunning = new LinearLayout(context);
		btnRunning.setOrientation(LinearLayout.HORIZONTAL);

		Button btnPauseAndResume = new Button(context);
		btnPauseAndResume.setBackgroundResource(R.drawable.btn_selector_left);

		Button btnCountAndReset = new Button(context);
		btnCountAndReset.setBackgroundResource(R.drawable.btn_selector_right);

		btnRunning.addView(btnPauseAndResume, params3);
		btnRunning.addView(btnCountAndReset, params3);
	}
}
