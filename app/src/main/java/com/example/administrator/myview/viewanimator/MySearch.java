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

/**
 * Created by Administrator on 2017/6/6.
 */

public class MySearch extends View {

    Paint paint;
    Path searchPath; //搜索的圆
    Path ciclePath; //外圆
    //获得宽高
    int w;
    int h;
    //这是保存截取时，返回的坐标点
    float serpos[];
    float cicpos[];
    //测量 path 的类
    PathMeasure measure;
    //动画产生的实时数据
    float value;

    public MySearch(Context context) {
        this(context,null);
    }

    public MySearch(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MySearch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获得屏幕的宽高
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        w = wm.getDefaultDisplay().getWidth();
        h = wm.getDefaultDisplay().getHeight();
        //初始化数据
        serpos = new float[2];
        cicpos = new float[2];
        //画笔
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setDither(true);
        paint.setStrokeWidth(6);
        paint.setAntiAlias(true);
        //初始化路径
        initPath();
        //初始化动画
        initAnimator();
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //移动画布的远点到屏幕中心
        canvas.translate(w/2,h/5);
        paint.setColor(Color.BLUE);
        //这是截取方法返回的路径
        Path dra = new Path();
        //解除硬件加速，不然没效果
        dra.reset();
        dra.lineTo(0,0);
        //设置当前需要测量的path
        measure.setPath(searchPath,false);
        //开始截取
        boolean s = measure.getSegment(measure.getLength()*value,measure.getLength(),dra,true);
        //绘制路径
        canvas.drawPath(dra,paint);
    }

    /**
     * 初始化路径
     */
    public void initPath(){

        paint.setColor(Color.BLUE);
        //搜索的圆
        searchPath = new Path();
        RectF rectF = new RectF(-50,-50,50,50);
        searchPath.addArc(rectF,45,359.9f);
        //辅助圆
        ciclePath = new Path();
        RectF rectF1 = new RectF(-100,-100,100,100);
        ciclePath.addArc(rectF1,45,359.9f);
        //测量类
        measure = new PathMeasure();
        measure.setPath(searchPath,false);
        //获取坐标
        measure.getPosTan(0,serpos,null);

        measure.setPath(ciclePath,false);
        //获取坐标
        measure.getPosTan(0,cicpos,null);
        //根据两点坐标绘制线
        searchPath.lineTo(cicpos[0],cicpos[1]);
    }

    /**
     * 初始化动画
     */
    public void initAnimator(){

        ValueAnimator animator = ValueAnimator.ofFloat(0,1);
        animator.setDuration(2600);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }
}
