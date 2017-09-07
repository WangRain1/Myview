package com.example.administrator.myview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.myview.R;

/**
 * Created by Administrator on 2017/5/25.
 */

public class ChartView extends View {


    float big_chart_r;
    float small_chart_r;
    int chart_bg;

    Paint paint ;

    public ChartView(Context context) {
        this(context,null);
    }

    public ChartView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.chartview,0,0);

        chart_bg = a.getColor(R.styleable.chartview_chart_bg,0xea0a0a);
        big_chart_r = a.getDimension(R.styleable.chartview_big_chart_r,30);
        small_chart_r = a.getDimension(R.styleable.chartview_small_chart_r,20);

        a.recycle();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(chart_bg);
    }

    int widthsize;
    int heightsize;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
        widthsize = MeasureSpec.getSize(widthMeasureSpec);
        heightsize= MeasureSpec.getSize(heightMeasureSpec);

        if (widthmode==MeasureSpec.EXACTLY){
            big_chart_r = widthsize/2;

        }else if (widthmode==MeasureSpec.AT_MOST){
            widthsize = (int)(big_chart_r*2);
            heightsize =(int)(big_chart_r*2);
        }

        setMeasuredDimension(widthsize,heightsize);
    }

    //设置当前进度
    int deg =0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画背景
        paint.setColor(chart_bg);
        RectF re = new RectF(0,0,widthsize,heightsize);
        canvas.drawArc(re,0,360,true,paint);
        //设置当前进度
        paint.setColor(0xFF0A7AEA);
        RectF re3 = new RectF(0,0,widthsize,heightsize);
        canvas.drawArc(re3,0,deg,true,paint);
        //画空的中心元
        paint.setColor(0xFFF2F2F2);

        RectF re2 = new RectF(20,20,widthsize-20,heightsize-20);
        canvas.drawArc(re2,0,360,true,paint);
        paint.setColor(0xFF0A7AEA);


        canvas.drawText((float)(deg/360.0)*100+"%",big_chart_r,big_chart_r,paint);

        if (deg >=360){
            handler.sendEmptyMessage(2);
        }else {
            handler.sendEmptyMessageDelayed(1,50);
        }
    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what==1){

                deg+=4;

                invalidate();
            }
        }
    };



}
