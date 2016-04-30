package com.example.pants.tooldemo.tool.tool_1.clock4;

import android.content.Context;
import android.graphics.Canvas;
import android.text.format.Time;
import android.widget.RelativeLayout;

public class Clock4 extends RelativeLayout {

	Context mContext;
	private int ratioOut = 240;
	private int ratioIn = 170;
	private int widthOut = 30;

	private float progress = 180;
	private Time time = new Time();

	public TimeDisplay timeDisplay;
	SecondLines secondLines;
	PointerOutline pointer;

	public Clock4(Context context) {
		super(context);
		mContext = context;

		timeDisplay = new TimeDisplay(mContext, this);
		secondLines = new SecondLines(mContext);
		pointer = new PointerOutline(mContext);

		LayoutParams params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);

		addView(timeDisplay, params);
		addView(secondLines);
		addView(pointer);

		// pointer.setOnTouchListener(new OnTouchListener() {
		// boolean pushDown = false;
		//
		// @Override
		// public boolean onTouch(View v, MotionEvent event) {
		// switch (event.getAction()) {
		// case MotionEvent.ACTION_DOWN:
		// pushDown = pointer.isDownIn(event.getX(), event.getY());
		// if (pushDown)
		// Toast.makeText(mContext, "down", Toast.LENGTH_LONG)
		// .show();
		// break;
		// case MotionEvent.ACTION_MOVE:
		// if (!pushDown)
		// return false;
		//
		// pointer.move(event.getX(), event.getY());
		// break;
		// case MotionEvent.ACTION_UP:
		// if (!pushDown)
		// return false;
		// pushDown = true;
		//
		// // operation
		// break;
		// }
		//
		// return true;
		// }
		// });
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	public void doFadeOutAnimation() {
		
	}

	public void doFadeInAnimation() {

	}
}
