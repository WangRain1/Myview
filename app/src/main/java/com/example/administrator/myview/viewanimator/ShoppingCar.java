package com.example.administrator.myview.viewanimator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/6/5.
 */

public class ShoppingCar extends View {

    public ShoppingCar(Context context) {
        this(context,null);
    }

    public ShoppingCar(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    Paint p;
    public ShoppingCar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        p = new Paint();
        p.setDither(true);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        p.setAntiAlias(true);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        p.setColor(Color.GREEN);
        RectF rectF = new RectF(0,0,100,100);
        canvas.drawOval(rectF,p);




    }
}
