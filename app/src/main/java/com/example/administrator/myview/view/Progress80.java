package com.example.administrator.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/5/25.
 */

public class Progress80 extends View {

    //定义一个接口，当动画结束时调用
    public interface AnimationIsComplete{

        public void isComplete();

    }

    //接口
    AnimationIsComplete isComplete;
    //设置接口
    public void setIsComplete(AnimationIsComplete isComplete) {
        this.isComplete = isComplete;
    }

    //颜色数组-----作用是让进度条实现渐变颜色
    int [] mColors;

    public Progress80(Context context) {
        this(context,null);
    }

    public Progress80(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public Progress80(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mColors = new int[]{
                0xFF4081,
                0xFFFFFF00,
                0x303F9F,
                0xFFFF0000,
                0xFF660099,
        };
    }
    //宽高
    int widthSize;
    int heightSize;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        widthSize= MeasureSpec.getSize(widthMeasureSpec);
        heightSize= MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(widthSize,heightSize);
    }

    int progress = 0;
    int line = 0;
    int line2 =0;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //实现颜色的过度渐变
        Shader s = new SweepGradient(0,0,mColors,null);
        //矩形
        RectF rectF = new RectF(15,15,widthSize-15,heightSize-15);
        Paint paint = new Paint();
        paint.setShader(s);
        paint.setStrokeWidth(10);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        //绘制圆弧，第三个参数，是从第二个参数的角度算起
        canvas.drawArc(rectF,120,progress,false,paint);

        if (progress<300){
            progress+=10;
        }

        if (progress>=300){

            if ((widthSize-70-line)>70){

                line +=7 ;

            }else
            if ((widthSize-70-line2)>70){
                line2 +=7;

                //这里动画结束调用接口
                isComplete.isComplete();
            }
            //设置线宽
            paint.setStrokeWidth(20);
            //画线
            canvas.drawLine(widthSize-70-line,heightSize-70-line,widthSize-70,heightSize-70,paint);
            canvas.drawLine(widthSize-70,70,widthSize-70-line2,70+line2,paint);
        }
        //重绘制
        postInvalidate();
    }
}
