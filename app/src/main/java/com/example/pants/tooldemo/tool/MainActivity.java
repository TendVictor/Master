package com.example.pants.tooldemo.tool;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.pants.tooldemo.R;
import com.example.pants.tooldemo.tool.tool_1.Activity_1;
import com.example.pants.tooldemo.tool.tool_2.Activity_2;
import com.example.pants.tooldemo.tool.tool_exp.MediaPlayerTest;
import com.example.pants.tooldemo.tool.tool_exp2.AudioFxActivity;


public class MainActivity extends AppCompatActivity {

    public static DisplayMetrics displayMetrics;
    public static int statusBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        displayMetrics = getMetrics(this);
        statusBarHeight = getStatusBarHeight();
    }

    private DisplayMetrics getMetrics(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()) {
            case R.id.btn1:
                intent = new Intent(MainActivity.this, Activity_1.class);
                break;
            case R.id.btn2:
                intent = new Intent(MainActivity.this, Activity_2.class);
                break;
            case R.id.btn3:
                intent = new Intent(MainActivity.this, MediaPlayerTest.class);
                break;
            case R.id.btn4:
                intent = new Intent(MainActivity.this, AudioFxActivity.class);
                break;
        }
        startActivity(intent);
    }

}
