package com.example.administrator.myview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.administrator.myview.R;

/**
 * Created by Administrator on 2017/5/24.
 */

public class Cview extends View {


    int width;

    int height;
    public Cview(Context context) {
        this(context,null);
    }

    public Cview(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public Cview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();




    }

    float x=0;
    float y=0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:


                Log.e(">>>>>>","11111111111111111");
                break;

            case MotionEvent.ACTION_MOVE:

                x = event.getRawX();
                y = event.getRawY();

                invalidate();

                break;

            case MotionEvent.ACTION_UP:

                Log.e(">>>>>>","33333333333333333");
                break;

        }

        return true;
    }
    Bitmap mDstBtimap ,mSrcBitmap;
    Canvas mDstCanvas;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //创建一个当前屏幕大小的dstBitmap并根据该dstBitmap创建相同大小的mDstCanvas
        mDstBtimap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mDstCanvas = new Canvas(mDstBtimap);
        mDstCanvas.drawColor(Color.parseColor("#ccc568"));

        //获得mSrcBitmap，并将其缩放至和屏幕大小相同
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mSrcBitmap = Bitmap.createScaledBitmap(mSrcBitmap, width, height, false);

        //先绘制底层的图片，然后绘制相同大小的mDstBtimap使其覆盖底层的图皮
        canvas.drawBitmap(mSrcBitmap, 0, 0, null);
        canvas.drawBitmap(mDstBtimap, 0, 0,null);

        Paint mPaint = new Paint();
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setColor(Color.argb(20,255,0,0));//设置此时的画笔为透明，所以可以看到底层的图片
        mDstCanvas.drawCircle(x, y, 100, mPaint);



    }
}
