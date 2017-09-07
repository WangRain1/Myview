package com.example.administrator.myview.matrixs;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.administrator.myview.R;

/**
 * Created by Administrator on 2017/8/30.
 */

public class MatrixTranslateView extends View{


    Camera camera;
    Matrix matrix;
    Paint paint;

    Bitmap bitmap;


    public MatrixTranslateView(Context context) {
        this(context,null);
    }

    public MatrixTranslateView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MatrixTranslateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        camera = new Camera();
        matrix = new Matrix();
        paint = new Paint();

        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        bitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.meinv);

        animator();

    }


    float y =0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        matrix.reset();
        camera.save();

        camera.rotateY(y);
        camera.getMatrix(matrix);
        camera.restore();
        paint.setColor(Color.BLUE);

        matrix.preTranslate(20, 20);
//        matrix.postTranslate(getWidth()/2, getHeight()/2);
        canvas.drawBitmap(bitmap,matrix,paint);
    }


    public void animator(){

        ValueAnimator animator = ValueAnimator.ofFloat(0,660);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                y = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(3000);
        animator.start();

    }



}
