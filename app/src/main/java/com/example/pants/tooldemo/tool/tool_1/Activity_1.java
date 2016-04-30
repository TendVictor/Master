package com.example.pants.tooldemo.tool.tool_1;

/**
 * Created by Pants on 2016/4/20.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.pants.tooldemo.R;
import com.example.pants.tooldemo.tool.tool_1.layout.ButtonLayout;
import com.example.pants.tooldemo.tool.tool_1.layout.DisplayLayout;
import com.example.pants.tooldemo.tool.tool_1.layout.ListPagerLayout;

public class Activity_1 extends Activity {


    public DisplayLayout displayLayout;
    public ListPagerLayout listLayout;
    public ButtonLayout buttonLayout;

    public static int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = (LinearLayout) View.inflate(this, R.layout.layout_1, null);
        setContentView(rootLayout);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 768);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 342);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 120);

        displayLayout = new DisplayLayout(this);
        listLayout = new ListPagerLayout(this);
        buttonLayout = new ButtonLayout(this);

        rootLayout.addView(displayLayout, params1);
        rootLayout.addView(listLayout, params2);
        rootLayout.addView(buttonLayout, params3);
    }

    public void changePage(int nextPage) {
        displayLayout.changeDisplay(currentPage, nextPage);
        listLayout.changePage(nextPage);
        buttonLayout.changePage(currentPage, nextPage);

        currentPage = nextPage;
    }
}
