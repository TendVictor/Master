package com.example.pants.tooldemo.tool.tool_1.clock1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.text.format.Time;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.example.pants.tooldemo.R;

@SuppressLint("NewApi")
public class Clock1 extends RelativeLayout {

	Context mContext;
	private int ratioOut = 240;
	private int ratioIn = 170;
	private int widthOut = 30;

	private float timeProgress = 180;
	private Time time = new Time();

	SecondLines secondLines;
	ClockOutline clockOutline;

	Pointer pointer;
	PointerHour pointH;
	PointerMinute pointM;

	float animProgress = 0;

	public Clock1(Context context) {
		super(context);
		mContext = context;

		secondLines = new SecondLines(mContext);
		clockOutline = new ClockOutline(mContext);
		pointer = new Pointer(mContext);
		pointH = new PointerHour(mContext);
		pointM = new PointerMinute(mContext);

		addView(secondLines);
		addView(clockOutline);
		addView(pointer);
		addView(pointH);
		addView(pointM);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	public void doFadeInAnimation() {
		Animation anim1 = AnimationUtils.loadAnimation(mContext, R.anim.fade_in_scale_big);
		secondLines.startAnimation(anim1);
		Animation anim2 = AnimationUtils.loadAnimation(mContext, R.anim.fade_in_scale_big_rotate_offset);
		pointer.startAnimation(anim2);

		Animation anim3 = AnimationUtils.loadAnimation(mContext, R.anim.fade_in_scale_small);
		clockOutline.startAnimation(anim3);

		Animation anim4 = AnimationUtils.loadAnimation(mContext, R.anim.fade_in_rotate_slow);
		pointM.startAnimation(anim4);

		Animation anim5 = AnimationUtils.loadAnimation(mContext, R.anim.fade_in_rotate);
		pointH.startAnimation(anim5);
	}
}
