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
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.administrator.myview.R;

import java.lang.reflect.Array;

/**
 * Created by Administrator on 2017/8/30.
 */

public class Matrix3DView extends View {


    Matrix matrix;
    Matrix matrix2;
    Camera camera;
    Paint paint;
    Bitmap bitmap;
    public Matrix3DView(Context context) {
        this(context,null);
    }

    public Matrix3DView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }


    public Matrix3DView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        matrix2 = new Matrix();
        matrix = new Matrix();
        camera = new Camera();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.meinv);
    }

    float xdeg =0;
    float ydeg = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getMeasuredWidth()/2,getMeasuredHeight()/2);

        matrix.reset();
        camera.save();
        camera.rotateX(xdeg);
        camera.rotateY(ydeg);
        camera.getMatrix(matrix);
        camera.restore();
        canvas.concat(matrix);

        Log.e(">>>",">>"+matrix.toString());
        matrix.preTranslate(-getWidth()/2,-getHeight()/2);
        matrix.postTranslate(-getWidth()/2,-getHeight()/2);

        canvas.drawBitmap(bitmap,-200,-100,paint);

    }



    float x;
    float y;
    float cy;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();

        switch (action){

            case MotionEvent.ACTION_DOWN:
                Log.e("-----","2222222"+x);
                x = event.getX();
                y = event.getY();
                return true;

            case MotionEvent.ACTION_MOVE:

                float cx = event.getX();
                cy = event.getY();

                float sx = cx -x;
                float sy = cy - y;

                rot( sx,  sy);
                return true;

            case MotionEvent.ACTION_UP:

                xdeg = 0;

                ydeg = 0;
                invalidate();

                return true;
        }

        return true;
    }


    public void rot(float cx, float cy){

        float px = cx / (getWidth()/2);
        float py = cy / (getHeight()/2);

        if (px>1){
            px = 1;
        }else if (px<-1){
            px = -1;
        }

        if (py>1){
            py = 1;
        }else if (py<-1){
            py = -1;
        }

        xdeg = 40*px;
        ydeg = 40*py;

        invalidate();

    }
}
