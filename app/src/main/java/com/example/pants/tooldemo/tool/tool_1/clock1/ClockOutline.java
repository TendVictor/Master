package com.example.pants.tooldemo.tool.tool_1.clock1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

public class ClockOutline extends View {

	Context mContext;
	private int ratioOut = 240;
	private int ratioIn = 170;
	private int widthOut = 30;

	private float progress = 180;

	Paint paint = new Paint();
	Path path = new Path();
	Matrix matrix = new Matrix();
	
	RectF rect;

	public ClockOutline(Context context) {
		super(context);
		mContext = context;

		rect = new RectF(ratioOut - ratioIn, ratioOut - ratioIn, ratioOut
				+ ratioIn, ratioOut + ratioIn);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// 画表框
		paint.setStyle(Style.STROKE);
		paint.setDither(true);
		paint.setAntiAlias(true);
		paint.setColor(0xeeffffff);
		paint.setStrokeWidth(1);

		canvas.drawArc(rect, 0, 360, true, paint);
	}
}
