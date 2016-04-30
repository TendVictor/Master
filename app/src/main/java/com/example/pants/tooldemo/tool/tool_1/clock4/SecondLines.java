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

public class SecondLines extends View {

	Context mContext;
	private int ratioOut = 240;
	private int ratioIn = 170;
	private int widthOut = 30;

	private float progress = 0;

	Paint paint = new Paint();
	Path path = new Path();
	Matrix matrix = new Matrix();
	RectF rect;

	Handler mHandler;

	boolean isRunning = false;

	public SecondLines(Context context) {
		super(context);
		mContext = context;

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			}
		};
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// 画刻度
		paint.setStyle(Style.STROKE);
		paint.setDither(true);
		paint.setAntiAlias(true);
		paint.setColor(0xffffffff);
		paint.setStrokeWidth(3);

		path.reset();
		path.moveTo(ratioOut, widthOut * 3);
		path.rLineTo(0, -widthOut);

		matrix.setRotate(1.6f, ratioOut, ratioOut);

		for (float i = 0; i < 360; i = i + 1.6f) {
			float alpha = 128;
			if (isRunning && i < progress) {
				alpha = 255;
			}

			paint.setAlpha((int) alpha);
			path.transform(matrix);
			canvas.drawPath(path, paint);
		}
	}

	public void updateProgress(int second, int millis) {
		isRunning = true;

		float progress = ((float) second * 10 + millis) / 600 * 360;
		setProgress(progress);
	}

	public void reset() {
		setProgress(0);
	}

	public void stop() {
		isRunning = false;

		postInvalidate();
	}

	private void setProgress(float progress) {
		this.progress = progress;
	}

}
