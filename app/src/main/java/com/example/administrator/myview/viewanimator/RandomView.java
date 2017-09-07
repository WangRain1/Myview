package com.example.administrator.myview.viewanimator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/6/22.
 */

public class RandomView extends View {


    Paint paint;

    //屏幕宽高
    int w;
    int h;

    public RandomView(Context context) {
        this(context,null);
    }

    public RandomView(Context context, @Nullable AttributeSet attrs) {
       this(context,attrs,0);
    }

    public RandomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        w = wm.getDefaultDisplay().getWidth();
        h = wm.getDefaultDisplay().getHeight();

        paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(w/2,h/2);
        Path path = new Path();
        path.moveTo(0,300);
        path.cubicTo(-300,150,-400,-380,0,-150);
        path.lineTo(0,-150);
        path.cubicTo(400,-380,300,150,0,300);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(4);
        canvas.drawPath(path,paint);

        paint.setColor(Color.BLUE);

        canvas.drawPoint(-300,150,paint);
        canvas.drawPoint(-400,-380,paint);

        canvas.drawLine(-500,0,500,0,paint);
        canvas.drawLine(0,-500,0,500,paint);

    }
}
