package com.example.administrator.myview.viewanimator;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/6/27.
 */

public class RoundScrollview extends ScrollView {


    public RoundScrollview(Context context) {
        this(context,null);
    }

    public RoundScrollview(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public RoundScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        Log.e(">>>>>oldl>"+t,oldt+"?????????-l-"+oldt);

    }
}
