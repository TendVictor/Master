package com.example.pants.tooldemo.tool.tool_1.clock3;

import android.content.Context;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.example.pants.tooldemo.R;

public class Clock3 extends RelativeLayout {

	Context mContext;
	private int ratioOut = 240;
	private int ratioIn = 170;
	private int widthOut = 30;

	public SecondLines secondLines;
	public Pointer pointer;
	public MillisClock millisClock;
	public TimeDisplay timeDisplay;

	public Clock3(Context context) {
		super(context);
		mContext = context;

		secondLines = new SecondLines(mContext);
		pointer = new Pointer(mContext);
		millisClock = new MillisClock(mContext);
		timeDisplay = new TimeDisplay(mContext, this);

		addView(secondLines);
		addView(pointer);
		addView(millisClock);
		addView(timeDisplay);

		LayoutParams params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		timeDisplay.setLayoutParams(params);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	public void doFadeInAnimation() {
		Animation anim1 = AnimationUtils.loadAnimation(mContext,
				R.anim.fade_in_scale_big);
		secondLines.startAnimation(anim1);
		pointer.startAnimation(anim1);

		Animation anim3 = AnimationUtils.loadAnimation(mContext,
				R.anim.fade_in_scale_small_from_verybig);
		millisClock.startAnimation(anim3);
		
		Animation anim4 = AnimationUtils.loadAnimation(mContext,
				R.anim.fade_in_quick);
		timeDisplay.startAnimation(anim4);
	}
}
