package com.example.administrator.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/5/25.
 */

public class ChartLineView extends View {

    //水平数据x
    String [] row;
    //竖直数据y
    String [] cols ;

    int [] data;

    Paint paint ;

    public ChartLineView(Context context) {
        this(context,null);

    }

    public ChartLineView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ChartLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        cols = new String[]{"0","10","20","30","40","50","60","70","80","90","100"};
        row = new String[]{"0","10","20","30","40","50","60","70","80","90","100"};
        data = new int[]{50,20,10,50,50,0,100,20,60};

        paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }


    int widthSize;
    int heightSize;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        widthSize= MeasureSpec.getSize(widthMeasureSpec);
        heightSize = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(widthSize,heightSize);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        //每个字的高度
        int yheiht = (heightSize-getTextHeight())/cols.length;
        for (int i=0;i<cols.length;i++){
            canvas.drawText(cols[cols.length-i-1],0,(yheiht*i)+getTextHeight(),paint);
        }
        int xspace = (int) ((widthSize-paint.measureText("100"))/row.length);

        for (int i=0;i<row.length;i++){
            if (i==0){
                canvas.drawText(row[i],xspace,heightSize-getTextHeight(),paint);
            }else {
                canvas.drawText(row[i],xspace*(i+1),heightSize-getTextHeight(),paint);
            }
        }

        for (int s=0;s<data.length;s++){

            float y = (((heightSize-getTextHeight())*data[s])/100);
            canvas.drawCircle(xspace*(s+1),(heightSize-getTextHeight())-y,7,paint);

            if (s>0){
                float y2 = (((heightSize-getTextHeight())*data[s-1])/100);
                canvas.drawLine(xspace*(s),(heightSize-getTextHeight())-y2,xspace*(s+1),(heightSize-getTextHeight())-y,paint);
            }
        }
    }

    int x =0;
    int y=0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                break;

            case MotionEvent.ACTION_MOVE:

                x = (int) event.getX();
                y = (int) event.getY();

                Log.e(">>>>>>>>>>"+y,"----------------"+x);

                break;

            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    /**
     *
     * @return
     */
    public int  getTextHeight(){

       Paint.FontMetrics fm =  paint.getFontMetrics();

        return (int) (fm.descent-fm.ascent);
    }
}
