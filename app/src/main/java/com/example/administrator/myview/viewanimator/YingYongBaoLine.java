package com.example.administrator.myview.viewanimator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.administrator.myview.R;
import com.example.administrator.myview.donghuaHelpclass.ViewPath;
import com.example.administrator.myview.donghuaHelpclass.ViewPathEvaluator;
import com.example.administrator.myview.donghuaHelpclass.ViewPoint;

/**
 * Created by Administrator on 2017/8/11.
 */

public class YingYongBaoLine extends RelativeLayout {


    int dip100;

    public YingYongBaoLine(Context context) {
        this(context,null);
    }

    public YingYongBaoLine(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public YingYongBaoLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        dip100 = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 100,  context.getResources()
                        .getDisplayMetrics());
        init(context);
    }

    ViewPath viewPath;


    ImageView bao;
    public void init(Context context){
        LayoutParams params = new LayoutParams(dip100,dip100);
        params.addRule(CENTER_IN_PARENT);
        bao = new ImageView(context);
        bao.setLayoutParams(params);
        bao.setImageResource(R.drawable.shape_circle_purple);
        addView(bao);


        /**
         * 让view加载完毕之后，再对view使用动画效果
         */
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rd = bao.getHeight();

                animatior();
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewPath = new ViewPath();
        viewPath.moveTo(0,0);
        viewPath.curveTo(-width/7,-width/9,-width/5,width/9,-width/6,width/6);
        viewPath.quadTo(-width/6,width/3,0,width/3);
        viewPath.curveTo(width/7,11*width/30,width/5,2*width/15,width/6,width/6);
        Log.e("====="+bao.getHeight(),"*********qqqqq*****"+rd);
        viewPath.quadTo(width/6,0,0,0);
    }

    float rd;

    int width;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getMeasuredWidth();
    }

    public void animatior(){

        ValueAnimator animator = ValueAnimator.ofFloat(bao.getHeight(),50);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float a = (float) animation.getAnimatedValue();

                bao.setPivotY(0);
                bao.setPivotX(rd/2);
                bao.setScaleX(a/bao.getHeight());
                bao.setScaleY(a/bao.getHeight());
            }
        });

        animator.setDuration(1000);

        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        animator.start();

        objanimator( bao,viewPath);

    }

    public void objanimator(ImageView bao,ViewPath p){

        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(new ViewObj(bao), "fabLoc", new ViewPathEvaluator(), p.getPoints().toArray());
        objectAnimator.setDuration(1000);
        objectAnimator.setStartDelay(1000);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();
    }

    public class ViewObj {
        private final ImageView red;

        public ViewObj(ImageView red) {
            this.red = red;
        }

        public void setFabLoc( ViewPoint newLoc) {
            red.setTranslationX(newLoc.x);
            red.setTranslationY(newLoc.y);
        }
    }
}
