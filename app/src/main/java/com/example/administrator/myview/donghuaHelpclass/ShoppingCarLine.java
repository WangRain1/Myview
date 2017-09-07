package com.example.administrator.myview.donghuaHelpclass;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import com.example.administrator.myview.viewanimator.ShoppingCar;

/**
 * Created by Administrator on 2017/6/5.
 */

public class ShoppingCarLine extends RelativeLayout {


    public interface CallOnclick{

        public void click();

    }
    CallOnclick onclick;

    public void setOnclick(CallOnclick onclick) {
        this.onclick = onclick;
    }

    ShoppingCar car;
    int w;
    int h;
    public ShoppingCarLine(Context context) {
        this(context,null);
    }

    public ShoppingCarLine(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ShoppingCarLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        w = wm.getDefaultDisplay().getWidth();
        h = wm.getDefaultDisplay().getHeight();
        car = new ShoppingCar(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(w-100,h/3-50,0,0);

        car.setLayoutParams(params);
        addView(car);

        car.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.click();
            }
        });

    }

    public void startAnimator(){

        path();
        animator(car,path);
    }


    public void animator(ShoppingCar car1,ViewPath viewPath){

        ObjectAnimator animator =  ObjectAnimator.ofObject(new ViewObj(car1),"fabLoc",new ViewPathEvaluator(),viewPath.getPoints().toArray() );

        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }


    ViewPath path;

    /**
     * 设置路径
     */
    public void path(){

        path = new ViewPath();

        path.moveTo(0,0);

        path.curveTo(300-(w-50),0,45-(w-50),(h/2-h/3),100-w,(h-100-h/3));
    }


    public class ViewObj {

        private final ShoppingCar red;

        public ViewObj(ShoppingCar red) {
            this.red = red;
        }

        public void setFabLoc(ViewPoint newLoc) {
            red.setTranslationX(newLoc.x);
            red.setTranslationY(newLoc.y);
        }
    }
}
