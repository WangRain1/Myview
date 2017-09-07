package com.example.administrator.myview.matrixs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.myview.R;

/**
 * Created by Administrator on 2017/8/31.
 */

public class Matrix3dReflectionView extends View {

    Matrix matrix;
    Matrix matrix2;
    Camera camera;
    Paint paint;
    Bitmap bitmap;


    public Matrix3dReflectionView(Context context) {
        this(context,null);
    }

    public Matrix3dReflectionView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public Matrix3dReflectionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        creatBitmap(canvas);
    }

    public void creatBitmap(Canvas canvas){
        Matrix matrix1 = new Matrix();
        matrix1.setScale(1.0f,-1.0f);
        Bitmap bitmap1 = Bitmap.createBitmap(
                bitmap,0,bitmap.getHeight()/2,bitmap.getWidth(),bitmap.getHeight()/2,matrix1,false);

        //两个bitmap 合起来的 bitmap
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), (int) ((bitmap.getHeight()*1.5)+5), Bitmap.Config.ARGB_8888);

//        Canvas canvas = new Canvas(bitmap2);
        canvas.drawBitmap(bitmap,0,0,null);
        canvas.drawBitmap(bitmap1,0,bitmap.getHeight()+5,null);

        LinearGradient gradient = new LinearGradient(0,bitmap.getHeight()+5,0,bitmap2.getHeight(),new int []{ 0x00ffffff,0xceffffff },new float[]{0.10f,0.9f}, Shader.TileMode.MIRROR);
        paint.setShader(gradient);
        RectF rectF = new RectF(0,bitmap.getHeight()+5,bitmap.getWidth(),bitmap2.getHeight());
        canvas.drawRect(rectF,paint);


    }
}
