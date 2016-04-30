package com.example.pants.tooldemo.tool.tool_1.clock4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.pants.tooldemo.R;

public class PointerOutline extends View {

	Context mContext;
	private int ratioOut = 240;
	private int ratioIn = 170;
	private int ratioSmall = 24;
	private int widthOut = 30;

	private float progress = 0;

	Paint paint = new Paint();
	Path path = new Path();
	Matrix matrix = new Matrix();

	RectF rect, rectSmall;
	Animation anim1, anim2;

	Handler mHandler;
	boolean hasStart = false;

	public PointerOutline(Context context) {
		super(context);
		mContext = context;

		rect = new RectF(2 + widthOut, 2 + widthOut, ratioOut * 2 - widthOut
				- 2, ratioOut * 2 - widthOut - 2);
		rectSmall = new RectF(ratioOut - ratioSmall, 2 + widthOut - ratioSmall,
				ratioOut + ratioSmall, 2 + widthOut + ratioSmall);

		anim1 = AnimationUtils.loadAnimation(mContext, R.anim.rotate_reverse_seconds);
		anim2 = AnimationUtils.loadAnimation(mContext, R.anim.rotate_reverse_minutes);

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {

				if (msg.arg1 == 0) {
					PointerOutline.this.clearAnimation();
					progress = msg.arg2;
					invalidate();
				} else if (msg.arg1 == 1) {
					progress = msg.arg2;
					invalidate();
					PointerOutline.this.startAnimation(anim1);
				}
			}
		};
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setDither(true);
		paint.setAntiAlias(true);
		paint.setColor(0xCCFFFFFF);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(2);

		canvas.drawArc(rect, progress, 264, false, paint);
		canvas.drawArc(rect, progress + 276, 84, false, paint);

		if (hasStart) {
			paint.setStyle(Style.FILL);
			float a = (float) (widthOut * 0.8f / 1.732f) * 2;
			path.reset();
			path.moveTo(ratioOut, widthOut * 2 - 6);
			path.rLineTo(a / 2, -widthOut * 0.8f);
			path.rLineTo(-a, 0);
			path.close();

			matrix.setRotate(progress, ratioOut, ratioOut);
			path.transform(matrix);
			canvas.drawPath(path, paint);
		} else {
			canvas.drawArc(rectSmall, 0, 360, false, paint);
		}

	}

	public void updateProgress(int second, int millis) {
		hasStart = true;

		float progress = ((float) second * 10 + millis) / 600 * 360;
		setProgress(1, progress);
	}

	public void stop(int second) {
		float progress = (float) second / 60 * 360;
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
		hasStart = false;
		postInvalidate();
	}

	public boolean isDownIn(float x, float y) {
		float cX = (float) (ratioOut + (ratioOut - widthOut - 2)
				* Math.cos((progress - 90) / 180 * Math.PI));
		float cY = (float) (ratioOut + (ratioOut - widthOut - 2)
				* Math.sin((progress - 90) / 180 * Math.PI));

		float distance = (cX - x) * (cX - x) + (cY - y) * (cY - y);
		return ratioSmall > Math.sqrt(distance);
	}

	// public void move(float x, float y) {
	// float cX = (float) (ratioOut + (ratioOut - widthOut - 2)
	// * Math.cos((progress - 90) / 180 * Math.PI));
	// float cY = (float) (ratioOut + (ratioOut - widthOut - 2)
	// * Math.sin((progress - 90) / 180 * Math.PI));
	// double tan = (x - cX) / (y - cY);
	//
	// progress = (float) (180 - Math.atan2(x - cX, y - cY) / Math.PI * 180);
	// postInvalidate();
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
