package com.example.pants.tooldemo.tool.tool_1.clock1;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.pants.tooldemo.R;

public class Pointer extends View {

	Context mContext;
	private int ratioOut = 240;
	private int ratioIn = 170;
	private int widthOut = 30;

	private float progress = 180;

	Paint paint = new Paint();
	Path path = new Path();
	Matrix matrix = new Matrix();

	Animation anim;

	Handler mHandler;

	public Pointer(Context context) {
		super(context);
		mContext = context;

		anim = AnimationUtils.loadAnimation(mContext, R.anim.rotate_seconds);

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				Pointer.this.startAnimation(anim);

				progress = msg.what;
			}
		};

		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				updateProgress();
			}
		}, 0, 500);
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
		path.moveTo(ratioOut, widthOut + 4);
		path.rLineTo(a / 2, widthOut * 0.8f);
		path.rLineTo(-a, 0);
		path.close();

		matrix.setRotate(progress + 2, ratioOut, ratioOut);
		path.transform(matrix);

		canvas.drawPath(path, paint);
	}

	private void updateProgress() {
		Time time = new Time();
		time.setToNow();
		float second = time.second;
		long millis = System.currentTimeMillis() % 1000;

		setProgress(360 * (second * 1000 + millis) / 60000);
	}

	public void setProgress(float progress) {
		mHandler.sendEmptyMessage((int) progress);
	}

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
