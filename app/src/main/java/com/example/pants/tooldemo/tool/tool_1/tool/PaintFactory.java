package com.example.pants.tooldemo.tool.tool_1.tool;

import android.graphics.Paint;

public class PaintFactory {

	public static final Paint paint = initialPaint();

	private static Paint initialPaint() {
		Paint myPaint = new Paint();
		myPaint.setAntiAlias(true);
		myPaint.setDither(true);

		return myPaint;
	}

	public static Paint getInstance() {
		return paint;
	}

	public static class Builder {
		public Builder(Paint.Style paintStyle, int paintColor, int paintStroke) {
			paint.setStyle(paintStyle);
			paint.setColor(paintColor);
			paint.setStrokeWidth(paintStroke);
		}
		
		public Paint build() {
			return paint;
		}
	}
}
