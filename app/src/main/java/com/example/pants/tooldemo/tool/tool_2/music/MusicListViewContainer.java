package com.example.pants.tooldemo.tool.tool_2.music;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pants.tooldemo.R;

import java.util.Iterator;
import java.util.List;


/**
 * Created by chen on 16/4/30.
 */
public class MusicListViewContainer extends LinearLayout{

    private List<Music> musics = null;
    private MusicProvider musicProvider= null;
    private int listNumber = 0;

    private int line1Width,line2Width,line3Width;

    private LinearLayout line1,line2,line3;

    private Context context = null;

    public MusicListViewContainer(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MusicListViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public MusicListViewContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }


    public void init(){
        LayoutInflater mInflater = LayoutInflater.from(context);
        View mView = mInflater.inflate(R.layout.listmusic_container, null);
        addView(mView);
        line1 = (LinearLayout) mView.findViewById(R.id.line1);
        line2 = (LinearLayout) mView.findViewById(R.id.line2);
        line3 = (LinearLayout) mView.findViewById(R.id.line3);

    }

    public void addMusicProvider(MusicProvider musicProvider){
        this.musicProvider = musicProvider;
    }

    //根据当前数据多少放入哪个listView中
    public void inputMusicListView() {
        musics = musicProvider.getList();

        Iterator<Music> iterator = musics.iterator();
        while(iterator.hasNext()){
            Music music = iterator.next();
            String title = music.getTitle();

        }
    }



    //添加歌名
    private void addTextView(Music music){
        TextView textView = new TextView(context);
        String title = music.getTitle();
        int titlelength = title.length();
        textView.setText(title);

    }


    //判断哪个列表最短
//    public int minWidthFromLinear(LinearLayout line1,LinearLayout line2,LinearLayout line3){
//        int minNumber = 1;
//        int min = line1.getWidth();
//        if(min > line2.getWidth()){
//            minNumber = 2;
//            min = line2.getWidth();
//        }
//        if(min > line3.getWidth()){
//            minNumber = 3;
//            min = line3.getWidth();
//        }
//        return minNumber;
//    }
}
