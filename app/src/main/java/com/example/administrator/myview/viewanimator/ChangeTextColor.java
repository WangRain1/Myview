package com.example.administrator.myview.viewanimator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/6/1.
 */

public class ChangeTextColor extends View {


    Paint paint ;

    String text = "这是一个改变字体颜色的控件";

    int [] mColors;

    public ChangeTextColor(Context context) {
        this(context,null);
    }

    public ChangeTextColor(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ChangeTextColor(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mColors = new int[]{
                0xFFEA0A0A,
                0xFFFFFF00,
                0xFF408100,
                0x303F9F,
                0xFFEA0A0A,
                0xFFFFFF00,
                0xFF408100,
                0x303F9F,
                0xFFEA0A0A,
                0xFFFFFF00,
                0xFF408100,
                0x303F9F,
                0xFFEA0A0A,
                0xFFFFFF00,
                0xFF408100,
                0x303F9F,
        };

        paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setTextSize(50);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

    }

    int x =0;

    int y=0;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (y>0){
            paint.setColor(mColors[y-1]);
        }else {

            paint.setColor(Color.BLACK);
        }
        canvas.drawText(text,30,50,paint);

        int textWidh = (int) paint.measureText(text);

        canvas.clipRect(30,0,30+x,textWidh+30);
        if (y>0){
            paint.setColor(mColors[y]);
        }else {

            paint.setColor(Color.RED);
        }
        canvas.drawText(text,30,50,paint);

        if (x<textWidh){
            x+=2;
            invalidate();
        }else {
            x=0;
            y++;
            invalidate();
        }
    }
}
