package com.example.administrator.myview.viewanimator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * Created by Administrator on 2017/6/12.
 */

public class RainView extends View {

    Paint paint;

    float mwidth;
    float mheight;

    public RainView(Context context) {
       this(context,null);
    }

    public RainView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public RainView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mwidth = getMeasuredWidth();
        mheight = getMeasuredHeight();

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Random random = new Random();
        int s = random.nextInt(30);

        canvas.drawLine(0,0,(int)((Math.random())*10),s+30,paint);

    }
}
