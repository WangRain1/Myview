package com.example.administrator.myview.viewanimator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by Administrator on 2017/6/6.
 * path 高级使用
 */

public class MySearch3 extends View {

    PathMeasure measure;

    Paint paint;
    int w;
    int h;
    float pos [];
    float pos2 [];
    float pos3 [];
    public MySearch3(Context context) {
        this(context,null);
    }

    public MySearch3(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MySearch3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        w = wm.getDefaultDisplay().getWidth();
        h = wm.getDefaultDisplay().getHeight();
        pos = new float[2];
        pos2 = new float[2];
        pos3 = new float[2];
        measure = new PathMeasure();

        paint = new Paint();
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
        paint.setAntiAlias(true);
        initP();
        initAnimator();

    }

    int i=0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(w/5,h/5);
        paint.setColor(Color.BLUE);

        Path linePath = new Path();

        Path searchPath = new Path();
        RectF rectF = new RectF(-50+value,-50,50+value,50);
        searchPath.addArc(rectF,45,359.9f);

        Path fuzhu = new Path();
        RectF rectF1 = new RectF(-100+value,-100,100+value,100);
        fuzhu.addArc(rectF1,45,359.9f);
        measure.setPath(fuzhu,false);
        measure.getPosTan(0,pos2,null);

        searchPath.lineTo(pos2[0],pos2[1]);

        //保存搜索的结束点的 坐标
        if (i==0){
            pos[0] = pos2[0];
            pos[1] = pos2[1];
            i = 9;
        }

        linePath.moveTo(pos[0],pos[1]);

        linePath.lineTo(pos[0]+value,pos[1]);

        canvas.drawPath(linePath,paint);

        measure.setPath(searchPath,false);
        Path p = new Path();
        //关闭硬件加速
        p.reset();
        p.lineTo(0,0);
        measure.getSegment(value,measure.getLength(),p,true);

        canvas.drawPath(p,paint);
    }


    float value = 0;
    float ending;

    /**
     * 制造一个虚拟的，初始化的对象，用来获取总长度(获取一次就够了)
     */
    public void initP(){
        Path seath = new Path();
        RectF rectF = new RectF(-50,-50,50,50);
        seath.addArc(rectF,45,359.9f);

        Path fuzhu2 = new Path();
        RectF rectF1 = new RectF(-100,-100,100,100);
        fuzhu2.addArc(rectF1,45,359.9f);
        measure.setPath(fuzhu2,false);
        measure.getPosTan(0,pos3,null);

        seath.lineTo(pos3[0],pos3[1]);

        measure.setPath(seath,false);
        ending = measure.getLength();
    }


    public void initAnimator(){

        ValueAnimator animator = ValueAnimator.ofFloat(0,ending).setDuration(2600);
        Log.e(">>>>>","-----------"+ ending);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}
