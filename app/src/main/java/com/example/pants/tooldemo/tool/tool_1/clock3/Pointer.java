package com.example.pants.tooldemo.tool.tool_1.clock3;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.pants.tooldemo.R;

@SuppressLint("NewApi")
public class Pointer extends View {

	Context mContext;
	private int ratioOut = 240;
	private int ratioIn = 170;
	private int widthOut = 30;

	private float progress = 0;

	Paint paint = new Paint();
	Path path = new Path();
	Matrix matrix = new Matrix();

	Animation anim;

	Handler mHandler;

	public Pointer(Context context) {
		super(context);
		mContext = context;

		anim = AnimationUtils.loadAnimation(mContext, R.anim.rotate_seconds);
		anim.setFillAfter(true);

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				progress = msg.arg2;

				if (msg.arg1 == 0) {
					Pointer.this.clearAnimation();
				} else if (msg.arg1 == 1) {
					Pointer.this.startAnimation(anim);
				}
			}
		};
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		paint.setStyle(Style.FILL);
		paint.setDither(true);
		paint.setAntiAlias(true);
		paint.setColor(0xCCFFFFFF);
		paint.setStrokeWidth(3);
		float a = (float) (widthOut * 0.8f / 1.732f) * 2;

		path.reset();
		path.moveTo(ratioOut, widthOut - 4);
		path.rLineTo(a / 2, -widthOut * 0.8f);
		path.rLineTo(-a, 0);
		path.close();

		matrix.setRotate(progress + 1, ratioOut, ratioOut);
		path.transform(matrix);

		canvas.drawPath(path, paint);
	}

	public void updateProgress(int second, int millis) {
		float progress = ((float) second * 10 + millis) / 600 * 360;
		setProgress(1, progress);
	}

	public void stop(int second, int millis) {
		float progress = ((float) second * 10 + millis) / 600 * 360;
		setProgress(0, progress);
	}

	public void setProgress(int code, float progress) {
		Message msg = new Message();
		msg.arg1 = code;
		msg.arg2 = (int) progress;
		mHandler.sendMessage(msg);
	}

	public void reset() {
		progress = 0;
		postInvalidate();
	}

	// public void start(float progress) {
	// Message msg = new Message();
	// msg.arg1 = 1;
	// msg.arg2 = (int) progress;
	// }
	//
	// public void stop() {
	// Message msg = mHandler.obtainMessage(0x123, 0, 0);
	// }

	// class MyThread extends Thread {
	// Handler mHandler;
	//
	// @Override
	// public void run() {
	// Looper.prepare();
	// mHandler = new Handler() {
	// public void handleMessage(Message msg) {
	// };
	// };
	// Looper.loop();
	// }
	// }
}
