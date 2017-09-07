package com.example.administrator.myview;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/8/22.
 */

public class OpenGlActy extends Activity {

    ListView listView;

    int visible;

    int first;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix);

        listView = (ListView) findViewById(R.id.list);
        Button button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ma(first,visible);

            }
        });

        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 100;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv=new TextView(OpenGlActy.this);
                tv.setPadding(20,20,20,20);
                tv.setText("position:"+position);
                return tv;
            }
        });


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


//                visible = visibleItemCount;
//                first = firstVisibleItem;

                Log.e(totalItemCount+">>>>>>>>>"+(visibleItemCount+firstVisibleItem-1),">>>>>>>>"+firstVisibleItem);
            }
        });
    }

    public void ma(int  s , int  e){

        ValueAnimator animator = ValueAnimator.ofInt(e,100);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int  y = (int) animation.getAnimatedValue();

                listView.smoothScrollToPosition(y);

            }
        });
        animator.setDuration(5000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}
