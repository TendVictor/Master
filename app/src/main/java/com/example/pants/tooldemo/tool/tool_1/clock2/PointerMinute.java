package com.example.pants.tooldemo.tool.tool_1.clock2;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.format.Time;
import android.view.View;

public class PointerMinute extends View {

	Context mContext;
	private int ratioOut = 240;
	private int ratioIn = 170;
	private int widthOut = 30;

	private float progress = 280;

	Paint paint = new Paint();
	Path path = new Path();
	Matrix matrix = new Matrix();
	RectF rect;

	public PointerMinute(Context context) {
		super(context);
		mContext = context;

		rect = new RectF(ratioOut - 10, ratioOut - 10, ratioOut + 10,
				ratioOut + 10);

		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				updateProgress();
			}
		}, 0, 3000);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		paint.setStyle(Style.STROKE);
		paint.setStrokeJoin(Join.ROUND);
		paint.setDither(true);
		paint.setAntiAlias(true);
		// 画环
		paint.setColor(0xFFEEEEEE);
		paint.setStrokeWidth(4);
		canvas.drawArc(rect, 0, 360, true, paint);
		// 画针
		path.reset();
		path.moveTo(ratioOut, ratioOut - 8);
		path.lineTo(ratioOut, ratioOut - ratioIn + 6);
		matrix.setRotate(progress, ratioOut, ratioOut);
		path.transform(matrix);
		canvas.drawPath(path, paint);
	}

	private void updateProgress() {
		Time time = new Time();
		time.setToNow();
		float minute = time.minute;
		float second = time.second;

		setProgress((minute * 60 + second) / 60 / 60 * 360);
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}
}
