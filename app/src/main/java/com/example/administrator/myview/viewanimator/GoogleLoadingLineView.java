package com.example.administrator.myview.viewanimator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.administrator.myview.donghuaHelpclass.Utils;

/**
 * Created by Administrator on 2017/6/2.
 */

public class GoogleLoadingLineView extends View {


    Paint paint;

    int lineWidth;

    int space;
    int w;

    int h;
    public GoogleLoadingLineView(Context context) {
        this(context,null);
    }

    public GoogleLoadingLineView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public GoogleLoadingLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        w = wm.getDefaultDisplay().getWidth();
        h = wm.getDefaultDisplay().getHeight();

        lineWidth = Utils.dp2px(context,80);
        space = Utils.dp2px(context,15);
        paint = new Paint();
    }


    int x=0;
    int y=0;

    int deg =0;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLUE);

        canvas.drawLine(y,0,y+lineWidth,0,paint);
        y +=(lineWidth+space);

        /**
         * --------------------------
         */
        Path path = new Path();
        path.moveTo(w-50,h/3);
        path.cubicTo(300,h/3,45,h/2,50,h-100);
        canvas.drawPath(path,paint);
        /**
         * -----------------------------
         */
        canvas.drawCircle(w/2,h/2,50,paint);

        canvas.translate(w/2,h/2);
        for (int s =0;s<6;s++){
            canvas.drawLine(50,0,60,0,paint);
            canvas.rotate(60);

        }

        for (int q =0;q<6;q++){
            canvas.rotate(-30);
            if (q==0||(q%2==0)){

                canvas.drawLine(50,0,100,0,paint);
            }
        }

        canvas.drawPoint(0,0,paint);


        canvas.rotate(deg);
        paint.setColor(Color.GREEN);
        canvas.drawLine(0,0,-50,0,paint);
        deg+=10;
        Log.e("--------","------cc--"+deg);



        postInvalidateDelayed(1000);
    }
}
