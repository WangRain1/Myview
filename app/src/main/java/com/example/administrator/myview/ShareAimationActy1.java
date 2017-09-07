package com.example.administrator.myview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/6/14.
 */

public class ShareAimationActy1 extends AppCompatActivity {


    int w;
    int h;
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share1);
        registerBoradcastReceiver();
        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
         w = wm.getDefaultDisplay().getWidth();
         h = wm.getDefaultDisplay().getHeight();

        imageView = (ImageView) findViewById(R.id.share1);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ShareAimationActy1.this,ShareAnimationActy2.class);

                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(ShareAimationActy1.this,(View)imageView,"shareview").toBundle());


                handler.sendEmptyMessageDelayed(1,200);
            }
        });
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ValueAnimator animator = ValueAnimator.ofFloat(0,(w-imageView.getWidth())/2);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float x = (float) animation.getAnimatedValue();
                    imageView.setX(x);
                }
            });

            animator.setDuration(200);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());

            ValueAnimator animat = ValueAnimator.ofFloat(0,(h-imageView.getHeight())/2);
            animat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float y = (float) animation.getAnimatedValue();
                    imageView.setY(y);
                }
            });

            animat.setDuration(200);
            animat.setInterpolator(new AccelerateDecelerateInterpolator());

            AnimatorSet set = new AnimatorSet();
            set.play(animat).with(animator);
            set.start();

        }
    };

    /**
     * 返回界面后的动画
     */
    public void backView(){

        ValueAnimator animator = ValueAnimator.ofFloat((w-imageView.getWidth())/2,0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (float) animation.getAnimatedValue();
                imageView.setX(x);
            }
        });

        animator.setDuration(300);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        ValueAnimator animat = ValueAnimator.ofFloat((h-imageView.getHeight())/2,0);
        animat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float y = (float) animation.getAnimatedValue();
                imageView.setY(y);
            }
        });

        animat.setDuration(300);
        animat.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet set = new AnimatorSet();
        set.play(animat).with(animator);
        set.start();
    }


    public void registerBoradcastReceiver(){
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("GONGXIANGDONGHUA");
        //注册广播
        registerReceiver(mBroadcastReceiver, myIntentFilter);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("GONGXIANGDONGHUA")){

                backView();

            }
        }
    };
}
