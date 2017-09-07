package com.example.administrator.myview.viewanimator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.administrator.myview.R;
import com.example.administrator.myview.donghuaHelpclass.Utils;

/**
 * Created by Administrator on 2017/8/23.
 */

public class MatrixTestView extends View{

    Bitmap bitmap;

    Paint paint;
    public MatrixTestView(Context context) {
        this(context,null);
    }

    public MatrixTestView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    int  w ;
    public MatrixTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


       w = Utils.dp2px(context,200);

        paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.meinv);

        a1 = new float[]{
                0,0,     //左上
                bitmap.getWidth(),0, // 右上
                bitmap.getWidth(),bitmap.getHeight(),//右下
                0,bitmap.getHeight(),   //左下
        };
        a2 = new float[]{
                0,0,     //左上
                bitmap.getWidth(),0, // 右上
                bitmap.getWidth(),bitmap.getHeight(),//右下
                0,bitmap.getHeight(),   //左下
        };

        matrix = new Matrix();
        matrix.setPolyToPoly(a1,0,a2,0,4);

        matrix.postScale(0.26f, 0.26f);
        matrix.postTranslate(0,200);
    }
    Matrix matrix;

    float a1[];
    float  a2[];
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.e(">>>>>>>>"+getMeasuredWidth(),"---"+bitmap.getWidth());
        canvas.drawBitmap(bitmap,matrix,paint);

        canvas.translate(getMeasuredWidth()/2,getMeasuredHeight()/2);

        paint.setColor(Color.GREEN);
        canvas.drawRect(0,0,getMeasuredWidth()/2,getMeasuredHeight()/2,paint);
    }
}
