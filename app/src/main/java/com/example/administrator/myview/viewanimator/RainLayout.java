package com.example.administrator.myview.viewanimator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;

import com.example.administrator.myview.R;

import java.util.Random;


/**
 * Created by Administrator on 2017/6/12.
 */

public class RainLayout extends RelativeLayout {


    private Context context;


    int position = -20;

    int w ;
    int h ;

    Random random;
    public RainLayout(Context context, AttributeSet attrs) {
        super(context,attrs);

        this.context = context;
        random = new Random();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        w = wm.getDefaultDisplay().getWidth();
        h = wm.getDefaultDisplay().getHeight();
        initRain();

    }


    /**
     * 初始化 雨 的对象
     */
    public void initRain(){
        //一次产生的雨量
        RainView rains = new RainView(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = (int) getResources().getDimension(R.dimen.start_position);
        int x = (int)((Math.random())*w);
        int z = (int)((Math.random())*10);
        if (z>5){
            x= -x;
        }
        params.leftMargin = x;
        rains.setLayoutParams(params);
        addView(rains);
        initAnimator(rains,x);
        handler.sendEmptyMessageDelayed(1,20);
    }


    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                initRain();
            }
        }
    };

    /**
     * 这是 y 轴 的坐标
     * @param rains
     */
    public void initAnimator(final RainView rains,int xstart){

        final ValueAnimator animator = ValueAnimator.ofFloat(-100,h);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float y = (float) animation.getAnimatedValue();
                rains.setY(y);

                postInvalidate();
            }
        });
        animator.setDuration(2000);
        animator.setInterpolator(new AccelerateInterpolator());

        ValueAnimator animatorx = ValueAnimator.ofFloat(xstart,xstart+400);

        animatorx.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (float) animation.getAnimatedValue();
                rains.setX(x);
                invalidate();
            }
        });
        animatorx.setDuration(2000);
        AnimatorSet set = new AnimatorSet();
        set.play(animator).with(animatorx);
        set.start();



        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                removeView(rains);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    public void remove(){
        handler.removeCallbacksAndMessages(null);

        if (handler!=null){
            handler = null;
        }
    }

}
