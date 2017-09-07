package com.example.administrator.myview.viewanimator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

/**
 * Created by Administrator on 2017/8/7.
 */

public class QuXianView extends View {


    Paint paint;
    Path path;
    Path path2;
    Path path3;
    Path path4;
    Path path5;


    //屏幕宽高
    int w;
    int h;
    public QuXianView(Context context) {
        this(context,null);
    }

    public QuXianView(Context context, @Nullable AttributeSet attrs) {
       this(context,attrs,0);
    }

    public QuXianView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        w = wm.getDefaultDisplay().getWidth();
        h = wm.getDefaultDisplay().getHeight();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setTextSize(w/5);
        tie = "载";

        animator();
        animator2();
    }

    String tie;

    float x = 0;
    float  y= 0;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path2 = new Path();
        path3 = new Path();
        path = new Path();
        path4 = new Path();
        path5 = new Path();
        canvas.translate(w/2,h/2);

        //绘制隐藏圆球
        paint.setStyle(Paint.Style.FILL);
        path.addCircle(0,0,w/4, Path.Direction.CW);
//        path2.reset();
        //绘制字体
        Rect r = new Rect();
        paint.getTextBounds(tie,0,tie.length(),r);
//        canvas.drawPath(path,paint);
        paint.setColor(Color.parseColor("#5A98F6"));
        canvas.drawText(tie,-r.width()/2,r.height()/2,paint);


        //第二条贝塞尔曲线
        paint.setColor(Color.parseColor("#96baf1"));
        path4.moveTo((7*w/8)+y,0);
        path4.lineTo((7*w/8)+y,w/4);
        path4.lineTo((-3*w/8)+y,w/4);
        path4.lineTo((-3*w/8)+y,0);
        path4.quadTo((-2*w/8)+y,w/28,(-w/8)+y,0);
        path4.quadTo(0+y,-w/28,(w/8)+y,0);
        path4.quadTo((2*w/8)+y,w/28,(3*w/8)+y,0);
        path4.quadTo((4*w/8)+y,-w/28,5*w/8+y,0);
        path4.quadTo((6*w/8)+y,w/28,(7*w/8)+y,0);
        path5.op(path,path4, Path.Op.INTERSECT);
        canvas.drawPath(path5,paint);
        canvas.clipPath(path5);

        paint.setColor(Color.parseColor("#FF4081"));
        canvas.drawText(tie,-r.width()/2,r.height()/2,paint);


        //第一条贝塞尔曲线
        paint.setColor(Color.parseColor("#5A98F6"));
        path2.moveTo((w/4)+x,0);
        path2.lineTo((w/4)+x,w/4);
        path2.lineTo((-3*w/4)+x,w/4);
        path2.lineTo((-3*w/4)+x,0);
        path2.quadTo((-5*w/8)+x,-w/20,(-w/2)+x,0);
        path2.quadTo((-3*w/8)+x,w/20,(-w/4)+x,0);
        path2.quadTo((-w/8)+x,-w/20,0+x,0);
        path2.quadTo((w/8)+x,w/20,(w/4)+x,0);
        path3.op(path,path2, Path.Op.INTERSECT);

        canvas.drawPath(path3,paint);

        canvas.clipPath(path3);

        paint.setColor(Color.WHITE);
        canvas.drawText(tie,-r.width()/2,r.height()/2,paint);

    }

    public void animator(){

        ValueAnimator animator = ValueAnimator.ofFloat(0,w/2);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                x = (float) animation.getAnimatedValue();

                postInvalidate();
            }
        });

        animator.setDuration(800);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }


    public void animator2(){

        ValueAnimator animator = ValueAnimator.ofFloat(0,-w/2);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                y = (float) animation.getAnimatedValue();

                postInvalidate();
            }
        });

        animator.setDuration(800);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }
}
