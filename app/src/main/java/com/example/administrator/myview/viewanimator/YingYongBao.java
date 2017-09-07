package com.example.administrator.myview.viewanimator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.administrator.myview.R;

/**
 * Created by Administrator on 2017/8/11.
 */

public class YingYongBao extends View {

    Paint paint;

    public YingYongBao(Context context) {
        this(context,null);

    }

    public YingYongBao(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public YingYongBao(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);

    }

    int w;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode==MeasureSpec.AT_MOST){

            w = 200;
        }
        setMeasuredDimension(w,w);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);

        RectF rectF = new RectF(0,0,w,w);
        canvas.drawOval(rectF,paint);

        Log.e("======",">>>>>>>>>"+paint.getColor());
    }

}
