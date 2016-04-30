package com.example.pants.tooldemo.tool.tool_1.buttongroup;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.example.pants.tooldemo.R;
import com.example.pants.tooldemo.tool.tool_1.tool.PaintFactory;

public class MiToggleButton extends RelativeLayout {

	private final int width = 74, height = 42;

	private int ballRatio = 15;
	private ToggleBall ball;

	private int ballColorOff = 0xffd0d0d0, ballColorOn = 0xffff7000;
	private int borderColor = 0xffc9c9c9;

	private RectF rectLeft = new RectF(1, 1, height - 1, height - 1);
	private RectF rectRight = new RectF(width - height + 1, 1, width - 1, height - 1);

	private boolean condition = false;
	private boolean isAnimPerforming = false;

	public MiToggleButton(Context context) {
		super(context);

		LayoutParams params1 = new LayoutParams(width, height);
		setLayoutParams(params1);

		setBackgroundColor(0xffffffff);

		ball = new ToggleBall(context);
		addView(ball);

		updateLocationOffBall();

		setOnClickListener(new ToggleButtonClickListener());
	}

	private void updateLocationOffBall() {
		LayoutParams params = (LayoutParams) ball.getLayoutParams();
		if (params == null) {
			params = new LayoutParams(ballRatio * 2 + 16, ballRatio * 2 + 16);
		}
		if (condition) {
			params.setMargins(36, 6, 8, 6);
		}
		else {
			params.setMargins(8, 6, 36, 6);
		}
		ball.setLayoutParams(params);
	}

	public void conditionChange() {
		if (isAnimPerforming)
			return;
		isAnimPerforming = true;

		performAnimation();
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				condition = !condition;
				isAnimPerforming = false;
			}
		}, 500);
	}

	private void performAnimation() {
		Animation anim;
		if (!condition) {
			anim = AnimationUtils.loadAnimation(getContext(), R.anim.translate_togglebutton_to_right);
		}
		else {
			anim = AnimationUtils.loadAnimation(getContext(), R.anim.translate_togglebutton_to_left);
		}
		anim.setFillAfter(true);
		ball.startAnimation(anim);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Paint paint = new PaintFactory.Builder(Style.STROKE, borderColor, 1).build();
		// draw border
		canvas.drawArc(rectLeft, 90, 180, false, paint);
		canvas.drawArc(rectRight, -90, 180, false, paint);

		canvas.drawLine(height / 2, 1, width - height / 2, 1, paint);
		canvas.drawLine(height / 2, height - 1, width - height / 2, height - 1, paint);
	}

	class ToggleBall extends View {
		public ToggleBall(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			int currentColor = getCurrentColor();
			Paint paint = new PaintFactory.Builder(Style.FILL, currentColor, 2).build();

			canvas.drawCircle(ballRatio, ballRatio, ballRatio, paint);
		}

		private int getCurrentColor() {
			if (condition)
				return ballColorOn;
			else
				return ballColorOff;
		}
	}

	class ToggleButtonClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			conditionChange();
		}
	}

	public boolean isCondition() {
		return condition;
	}

}
