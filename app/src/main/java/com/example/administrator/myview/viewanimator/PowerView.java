package com.example.administrator.myview.viewanimator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.administrator.myview.R;

/**
 * Created by Administrator on 2017/7/13.
 */

public class PowerView extends View {

    Paint paint;
    public PowerView(Context context) {
        this(context,null);
    }

    public PowerView(Context context, @Nullable AttributeSet attrs) {
       this(context,attrs,0);
    }

    public PowerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.parseColor("#d873ca85"));

        init(context);

    }

    Bitmap bm;

    Bitmap newBm;

    public void init(Context context){

        bm = BitmapFactory.decodeResource(context.getResources(),R.mipmap.blue);

    }

    int width;
    int heitht;

    int sizeWidth;
    int sizeHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeheight = MeasureSpec.getMode(heightMeasureSpec);
        sizeWidth= MeasureSpec.getSize(widthMeasureSpec);
        sizeHeight= MeasureSpec.getSize(heightMeasureSpec);

        if (modeWidth == MeasureSpec.EXACTLY){
            width = sizeWidth;
        }else {
            width = bm.getWidth();
        }
        if (modeheight == MeasureSpec.EXACTLY){
            heitht = sizeHeight;
        }else {
            heitht = bm.getHeight();
        }

        setMeasuredDimension(width,heitht);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        newBm = zoomImg(bm, width , heitht);
        y = newBm.getHeight();
        animator();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (newBm!=null){
            RectF rec = new RectF(0,0,sizeWidth,sizeHeight);

            Log.e(">>>>>>>"+sizeHeight,">>>>>>>"+sizeWidth);
            canvas.drawOval(rec,paint);

//            canvas.drawBitmap(newBm,new Matrix(),paint);

            Rect rect1 = new Rect(0,y,newBm.getWidth(),newBm.getHeight());
            Rect rectF = new Rect(0,y,newBm.getWidth(),newBm.getHeight());

            canvas.drawBitmap(newBm,rect1,rectF,paint);
        }
    }

    int y;

    public void animator(){

        ValueAnimator animator = ValueAnimator.ofInt(newBm.getHeight(),0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                y = (int ) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(5000);
        animator.start();



    }

    /**
     * 根据 设置的宽高来修改 图片的宽高
     * @param b
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap zoomImg(Bitmap b, int newWidth ,int newHeight){
        // 获得图片的宽高
        int width = b.getWidth();
        int height = b.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片   www.2cto.com
        Bitmap newbm = Bitmap.createBitmap(b, 0, 0, width, height, matrix, true);
        return newbm;
    }
}
