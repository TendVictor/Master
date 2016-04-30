package com.example.pants.tooldemo.tool.tool_1.clock3;

import java.util.Timer;
import java.util.TimerTask;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.pants.tooldemo.R;

public class MillisClock extends View {

	Context mContext;
	private int ratioOut = 240;
	private int ratioSmall = 40;

	private float progress = 180;

	Paint paint = new Paint();
	Path path = new Path();
	Matrix matrix = new Matrix();

	RectF rect1, rect2;

	Animation anim;
	Handler mHandler;

	public MillisClock(Context context) {
		super(context);
		mContext = context;

		rect1 = new RectF(ratioOut - ratioSmall, ratioOut + 100 - ratioSmall,
				ratioOut + ratioSmall, ratioOut + 100 + ratioSmall);
		rect2 = new RectF(ratioOut - 4, ratioOut + 100 - 4, ratioOut + 4,
				ratioOut + 100 + 4);

		anim = AnimationUtils.loadAnimation(mContext, R.anim.rotate_millis);
		anim.setFillAfter(true);

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				progress = msg.arg2;

				if (msg.arg1 == 0) {
					MillisClock.this.clearAnimation();
				} else if (msg.arg1 == 1) {
					MillisClock.this.startAnimation(anim);
				}
			}
		};
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// 画表框
		paint.setStyle(Style.STROKE);
		paint.setDither(true);
		paint.setAntiAlias(true);
		paint.setColor(0xffffffff);
		paint.setStrokeWidth(1);

		canvas.drawArc(rect1, 0, 360, false, paint);

		// 画表针
		paint.setColor(0xFFFFFFFF);
		paint.setStrokeWidth(3);
		canvas.drawArc(rect2, 0, 360, false, paint);

		paint.setStrokeWidth(2);
		path.reset();
		path.moveTo(ratioOut, ratioOut + 100 - 2);
		path.lineTo(ratioOut, ratioOut + 100 - ratioSmall + 6);
		matrix.setRotate(progress, ratioOut, ratioOut + 100);
		path.transform(matrix);
		canvas.drawPath(path, paint);
	}

	public void updateProgress(int millis) {
		setProgress(1, (float) (millis) / 10 * 360);
	}

	public void stop(int millis) {
		setProgress(0, (float) (millis) / 10 * 360);
	}

	public void setProgress(int code, float progress) {
		Message msg = new Message();
		msg.arg1 = code;
		msg.arg2 = (int) progress;
		mHandler.sendMessage(msg);
	}

	public void reset() {
		progress = 0;
	}

}
