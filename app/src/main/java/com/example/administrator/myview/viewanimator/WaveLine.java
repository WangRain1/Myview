package com.example.administrator.myview.viewanimator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

import com.example.administrator.myview.R;

/**
 * Created by Administrator on 2017/8/14.
 */

public class WaveLine extends View {

    Paint paint;
    //屏幕宽高
    int w;
    int h;

    float xy [];

    public UpDownListener listener;


    public WaveLine(Context context) {
        this(context,null);
    }

    public WaveLine(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public WaveLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray td = context.obtainStyledAttributes(attrs, R.styleable.WaveLine,0,0);
        height = td.getDimension(R.styleable.WaveLine_height,300);


        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        w = wm.getDefaultDisplay().getWidth();
        h = wm.getDefaultDisplay().getHeight();

        paint = new Paint();

        paint.setDither(true);
        paint.setAntiAlias(true);

        paint.setStrokeWidth(5);
        pathHead = new Path();
        measure = new PathMeasure();
        xy = new float[2];
        animator();
    }

    Path pathLine;
    Path pathWave;
    Path pathHead;

    PathMeasure measure;
    float height;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(w, (int) height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        canvas.translate(getMeasuredWidth()/2,height-w/56);

        //绘制波浪
        paint.setColor(Color.parseColor("#FF4081"));
        pathWave = new Path();
        pathWave.moveTo(w/2+x,0);
        pathWave.lineTo(w/2+x,-(height - w/56));
        pathWave.lineTo(-6*w/4+x,-(height - w/56));
        pathWave.lineTo(-6*w/4+x,0);

        pathWave.quadTo(-5*w/4+x,-w/28,-w+x,0);
        pathWave.quadTo(-3*w/4+x,w/28,-w/2+x,0);
        pathWave.quadTo(-w/4+x,-w/28,0+x,0);
        pathWave.quadTo(w/4+x,w/28,w/2+x,0);
        canvas.drawPath(pathWave,paint);

        //绘制直线
        pathLine = new Path();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        pathLine.addRect(0,-(height-w/56),3,w/56, Path.Direction.CCW);
//        canvas.drawPath(pathLine,paint);
        //获取相交 的 path路径
        pathHead.op(pathWave,pathLine, Path.Op.INTERSECT);
        //绘制直线
        paint.setColor(Color.WHITE);
        canvas.drawPath(pathHead,paint);
        //获取相交 path 的0点坐标 ，回调 返回到activity 中
        measure.setPath(pathHead,true);
        measure.getPosTan(0,xy,null);
        listener.listen(xy[1]);
//        canvas.drawCircle(xy[0],xy[1],35,paint);
        Log.e("---","xxxxxxx="+xy[1]);
    }

    float x = 0;
    public void animator(){

        ValueAnimator animator = ValueAnimator.ofFloat(0,w).setDuration(2000);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                x = (float) animation.getAnimatedValue();

                postInvalidate();
            }
        });
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }


    public void setListener(UpDownListener listener) {
        this.listener = listener;
    }

    public interface UpDownListener{

        public void listen(float a);

    }

}
