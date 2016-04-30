package com.example.pants.tooldemo.tool.tool_1.clock3;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimeDisplay extends LinearLayout {

	TextView tMillis;
	TextView tSecond;
	TextView tMinute;
	TextView tHour;

	int millis, second, minute, hour;
	Timer timer = null;
	Handler mHandler;

	Context mContext;
	Clock3 mClock;
	boolean showHour = false;
	boolean hasStart = false;

	public TimeDisplay(Context context, Clock3 clock) {
		super(context);
		mContext = context;
		mClock = clock;
		setOrientation(LinearLayout.HORIZONTAL);

		initTextView();
		addTextView();
		updateTime();

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				updateTime();
			}
		};
	}

	private void initTextView() {
		tMillis = new TextView(mContext);
		tSecond = new TextView(mContext);
		tMinute = new TextView(mContext);
		tHour = new TextView(mContext);

		tMillis.setTextColor(0xffffffff);
		tSecond.setTextColor(0xffffffff);
		tMinute.setTextColor(0xffffffff);
		tHour.setTextColor(0xffffffff);
		tMillis.setTextSize(32);
		tSecond.setTextSize(32);
		tMinute.setTextSize(32);
		tHour.setTextSize(32);
	}

	private void addTextView() {
		addView(tHour);
		addView(tMinute);
		addView(tSecond);
		addView(tMillis);
	}

	private void updateTime() {
		if (millis == 10) {
			millis = 0;
			++second;
			if (second == 60) {
				second = 0;
				++minute;
				if (minute == 60) {
					minute = 0;
					++hour;
					showHour = true;
				}
			}
		}

		tMillis.setText(millis + "");
		String ss = (second < 10) ? "0" + second : "" + second;
		tSecond.setText(ss + ".");
		String sm = (minute < 10) ? "0" + minute : "" + minute;
		tMinute.setText(sm + ":");
		if (showHour) {
			String sh = (hour < 10) ? "0" + hour : "" + hour;
			tHour.setText(sh + ":");
		}
	}

	public void startTime() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			int count = 0;

			@Override
			public void run() {
				++millis;
				mHandler.sendEmptyMessage(0x123);
				mClock.secondLines.updateProgress(second, millis);
				if (count++ % 5 == 0) {
					mClock.pointer.updateProgress(second, millis);
					mClock.millisClock.updateProgress(millis);
				}
			}
		}, 0, 100);
	}

	public void stopTime() {
		mClock.pointer.stop(second, millis);
		mClock.millisClock.stop(millis);
		mClock.secondLines.stop();

		timer.cancel();
		timer = null;
	}

	public void resetTime() {
		millis = 0;
		second = 0;
		minute = 0;
		hour = 0;

		mHandler.sendEmptyMessage(0x123);
		mClock.pointer.reset();
		mClock.secondLines.reset();
		mClock.millisClock.reset();
	}

	public long getTime() {
		return ((hour * 60 + minute) * 60 + second) * 10 * millis;
	}
}
