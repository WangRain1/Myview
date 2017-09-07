package com.example.administrator.myview.viewanimator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.example.administrator.myview.R;

/**
 * Created by Administrator on 2017/7/14.
 */
public class XformodeView extends View {

    Paint paint ;

    //屏幕宽高
    int w;
    int h;
    //定义一个矩形的高度变化
    float y;

    //xformode 的 类型 选择
    PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    //图片
    Bitmap bitmap;

    public XformodeView(Context context) {
        this(context,null);
    }

    public XformodeView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public XformodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //画笔
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);

        init(context);
    }

    /**
     * 初始化
     * @param context
     */
    public void init(Context context){
        //获得屏幕宽高
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        w = wm.getDefaultDisplay().getWidth();
        h = wm.getDefaultDisplay().getHeight();
        //加载bitmap 图片
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.xxx);
        //开始动画
        animator();
    }

    /**
     * 测量view
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(bitmap.getWidth(),bitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 绘制 图片 剪切 画布 控制图片显示
         */
//        Path path = new Path();
//        path.moveTo(0,y);
//
//        path.lineTo(bitmap.getWidth(),y);
//        path.lineTo(bitmap.getWidth(),bitmap.getHeight());
//        path.lineTo(0,bitmap.getHeight());
//        canvas.clipPath(path);


        /**
         * 设置图层
         */
        int layer = canvas.saveLayer(0,0,w,h,paint,Canvas.ALL_SAVE_FLAG);
        //绘制背景图片
        canvas.drawBitmap(bitmap,0,0,paint);
        //设置 xformode 模式
        paint.setXfermode(xfermode);
        //绘制矩形
        paint.setColor(Color.RED);
        RectF rectF = new RectF(0,y,bitmap.getWidth(),bitmap.getHeight());
        canvas.drawRect(rectF,paint);
        //最后设置为空
        paint.setXfermode(null);
        canvas.restoreToCount(layer);
    }

    /**
     * 动画
     */
    public void animator(){

        ValueAnimator animator = ValueAnimator.ofFloat(bitmap.getHeight(),0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                y = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(3000);
        animator.start();


    }
}
