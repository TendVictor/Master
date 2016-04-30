package com.example.pants.tooldemo.tool.tool_1.clock2;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.format.Time;
import android.util.Log;
import android.view.View;

public class SecondLines extends View {

	Context mContext;
	private int ratioOut = 240;
	private int ratioIn = 170;
	private int widthOut = 30;

	private float progress = 180;

	Paint paint = new Paint();
	Path path = new Path();
	Matrix matrix = new Matrix();
	RectF rect;

	public SecondLines(Context context) {
		super(context);
		mContext = context;

		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				updateProgress();
			}
		}, 0, 200);
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
		path.moveTo(ratioOut, widthOut * 2);
		path.lineTo(ratioOut, widthOut);

		matrix.setRotate(1.6f, ratioOut, ratioOut);

		for (float i = 0; i < 360; i = i + 1.6f) {
			float alpha = 128;
			float _i = i;
			if (progress < 120 && progress + 360 - _i < 120) {
				_i = _i - 360;
			}
			if (_i < progress && progress - _i < 120) {
				float rate = (progress - _i) / 120;
				alpha = 256 - rate * 128;
			}

			paint.setAlpha((int) alpha);
			path.transform(matrix);
			canvas.drawPath(path, paint);
		}

	}

	private void updateProgress() {
		Time time = new Time();
		time.setToNow();
		float second = time.second;
		long millis = System.currentTimeMillis() % 1000;

		setProgress(360 * (second * 1000 + millis) / 60000);
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}
}
