package com.example.administrator.myview.viewanimator;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/7/10.
 */

public class BeiSaiLineView extends View {



    public BeiSaiLineView(Context context) {
        this(context,null);
    }

    public BeiSaiLineView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public BeiSaiLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);






    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);




    }
}
