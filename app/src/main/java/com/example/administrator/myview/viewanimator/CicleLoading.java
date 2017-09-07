package com.example.administrator.myview.viewanimator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by Administrator on 2017/6/9.
 */

public class CicleLoading extends View {



    Paint paint;
    Path pathCicle;
    Path pathRecf;
    Path path1;
    int [] mColors;
    float [] lin;

    public CicleLoading(Context context) {
       this(context,null);
    }

    public CicleLoading(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CicleLoading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        lin = new float[]{
                0.2f,0.5f,0.8f,1f
        };
        mColors = new int[]{
                0xffcc0000,//red
                0xFF34A350,//ye
                0xFF375BF1,//b
                0xFF34A350,//g
        };

        Shader shader = new LinearGradient(100,100,-100,-100,mColors,lin,Shader.TileMode.REPEAT);

        paint = new Paint();
//        paint.setShader(shader);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth(1);

        initAnimator();
        initYAnimator();
    }

    float y =0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getMeasuredWidth()/2,getMeasuredHeight()/2);
        paint.setStyle(Paint.Style.FILL);
        pathCicle = new Path();
        RectF rectF = new RectF(-100,-100,100,100);
        pathCicle.addOval(rectF, Path.Direction.CW);
        pathRecf = new Path();
        path1 = new Path();

//        pathRecf.addRect(rectF2, Path.Direction.CW);
        pathRecf.moveTo(100+x,100);
        pathRecf.lineTo(-300+x,100);
        pathRecf.lineTo(-300+x,100-y);
        pathRecf.quadTo(-250+x,100-y-30,-200+x,100-y);

        if (y==200){
            pathRecf.quadTo(-150+x,100-y,-100+x,100-y);
            invalidate();

        }else{
            pathRecf.quadTo(-150+x,100-y+30,-100+x,100-y);
        }

        pathRecf.quadTo(-50+x,100-y-30,x,100-y);
        pathRecf.quadTo(50+x,100-y+30,100+x,100-y);

//        canvas.drawPath(pathRecf,paint);
        path1.op(pathRecf,pathCicle, Path.Op.INTERSECT);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor("#A3CDED"));
        canvas.drawPath(path1,paint);
        RectF re = new RectF(-101,-101,101,101);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#A3CDED"));
        canvas.drawOval(re,paint);




    }


    /**
     * y轴的 坐标改变动画
     */
    public void initAnimator(){

        ValueAnimator animator = ValueAnimator.ofFloat(0,200).setDuration(4000);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                y = (float) animation.getAnimatedValue();

                invalidate();
            }
        });
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    float x =0;

    /**
     * x轴坐标改变动画
     */

    public void initYAnimator(){

        final ValueAnimator animator = ValueAnimator.ofFloat(0,200).setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                x = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setRepeatCount(1);
        animator.start();

    }
}
