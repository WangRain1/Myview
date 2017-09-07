package com.example.administrator.myview.viewanimator;

import android.animation.Animator;
import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;

import com.example.administrator.myview.zhenduiviewzilei.Point;
import com.example.administrator.myview.zhenduiviewzilei.PointEvaluator;

/**
 * Created by Administrator on 2017/5/27.
 */

public class AnimatorCicle extends View{


    int radius = 20;
    Paint paint;

    Point p3;
    //第一个view动画结束
    boolean isLineEnd = false;

    boolean isLineEnd2 = false;

    public AnimatorCicle(Context context) {
        this(context,null);
    }

    public AnimatorCicle(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public AnimatorCicle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setDither(true);
        paint.setAntiAlias(true);

    }
    int wSize;
    int hSize;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        wSize= MeasureSpec.getSize(widthMeasureSpec);
        hSize= MeasureSpec.getSize(heightMeasureSpec);
        endX =wSize/2;
        endY =hSize/5*3;

        setMeasuredDimension(wSize,hSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if (p3 == null) {
            p3 = new Point(wSize/2,hSize/5);
            drawCircle(canvas);
            //设置第一个圆球，并且增加动画
            lineAnimator();
        } else {
            //第一个动画结束，重绘制，第二个动画
            if (isLineEnd){

//                draw2(canvas);
                drawscale( canvas);
            }else if (isLineEnd2){
                draw2(canvas);
            }
            else {
                //第一个动画
                drawCircle(canvas);
            }

        }

    }

    /**
     * 第一个动画
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        paint.setColor(Color.BLUE);
        canvas.drawCircle(p3.getX(),p3.getY(),radius,paint);

    }

    //结束时的位置
    float endX ;
    float endY;
    int times = 20;
    /**
     * 第二个元
     */
    public void draw2(Canvas canvas){
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        RectF f = new RectF(endX-4*radius,(endY-4*radius)-4*radius,endX+4*radius,endY);

        canvas.drawArc(f,90,-times,false,paint);
        canvas.drawArc(f,90,times,false,paint);
        handler.sendEmptyMessageDelayed(1,50);

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (times>=180){

            }else {
                times+=2;
                postInvalidate();
            }

        }
    };
    ValueAnimator oa;
    /**
     * 注意 ：：：：：其中的  addUpdateListener() 只能启动一次，多次启动没有作用，所以ondraw方法中药增加判断
     */
    public void lineAnimator( ){

        //开始点
        Point p1 = new Point(wSize/2,hSize/5);
        //结束点
        Point p2 = new Point(wSize/2,hSize/5*3);
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(),p1,p2);

        /**
         * 循环 获取p1 到  p2 之间的 坐标赋值给 p3，，，，这是个过程（自己循环）
         */
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
               p3 = (Point) animation.getAnimatedValue();

                postInvalidate();
            }
        });
        animator.setInterpolator(new BounceInterpolator());
        animator.setDuration(3000);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                //播放第二个动画
                isLineEnd = true;
                scaleAni();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    float scaleType =20;

    public void scaleAni(){
        oa= ValueAnimator.ofObject(new FloatEvaluator(),20,300);

        oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float w = (float) animation.getAnimatedValue();

                if (w<=150){
                    scaleType = w+10;
                }else {
                    scaleType = 150-(w-150)+10;
                }
                postInvalidate();
            }
        });
        oa.setDuration(3000);
        oa.setInterpolator(new AccelerateDecelerateInterpolator());
        oa.start();
        oa.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                isLineEnd = false;
                isLineEnd2 =true;

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void drawscale(Canvas canvas){
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(p3.getX(),p3.getY(),scaleType,paint);

    }
}
