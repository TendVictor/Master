package com.example.pants.tooldemo.tool.tool_1.clock2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
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

		rect = new RectF(4, 4, ratioOut * 2 - 4, ratioOut * 2 - 4);
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

		canvas.drawArc(rect, 3, 84, false, paint);
		canvas.drawArc(rect, 93, 84, false, paint);
		canvas.drawArc(rect, 183, 84, false, paint);
		canvas.drawArc(rect, 273, 84, false, paint);

		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setTextSize(18);

		canvas.drawText("12", ratioOut - 10, 14, paint);
		canvas.drawText("3", ratioOut * 2 - 10, ratioOut + 5, paint);
		canvas.drawText("6", ratioOut - 6, ratioOut * 2 - 2, paint);
		canvas.drawText("9", 0, ratioOut + 5, paint);
	}
}
