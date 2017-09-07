package com.example.administrator.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/5/26.
 */

public class BSPathView extends View {

    int width;

    int height;

    Paint paint;

    int [] mColors;

    public BSPathView(Context context) {
        this(context,null);
    }

    public BSPathView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public BSPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
        mColors = new int[]{
                0xFFEA0A0A,
                0xFFFFFF00,
                0xFF408100,
                0x303F9F,

        };

        Shader s = new SweepGradient(0,0,mColors,null);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setShader(s);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

    }


    float x=500;
    float y=100;

    boolean isLine = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                y = event.getY();
                isLine = true;
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                y = event.getY();
                isLine = true;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                y = event.getY();
                isLine = false;
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        paint.setColor(Color.argb(255,228,121,121));

        if (isLine){

            Path path = new Path();

            path.moveTo(x,y+150);

            path.quadTo(x-100,y,x,y-150);
            canvas.drawPath(path,paint);

        }else {
            //画中间的线
            canvas.drawLine(x,y-150,x,y+150,paint);
        }
        //画上半部的线
        canvas.drawLine(x,y-150,x,50,paint);
        //画下半部的线
        canvas.drawLine(x,y+150,x,height-(height/5),paint);

    }
}
