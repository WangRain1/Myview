package com.example.administrator.myview.viewanimator;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/8/16.
 */

public class MyS extends ScrollView {

    Sc sc;

    float rate = 0.4f;

    public void setSc(Sc sc) {
        this.sc = sc;
    }

    public MyS(Context context) {
        super(context);
    }

    public MyS(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyS(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float x ;
    float y;


    /**
     *
     *
     * 问题总结：
     * 1.action.down 不起作用，应为事件被 view 截取 ， 父布局 scrollview 获取不到 down 事件
     * 2.getScrollY()  ---   是指获取顶部滑出的距离，这个时候可以利用 为 0 的时候 获取 ACTION.move
     * 第一个触摸点，代替ACTION.DOWN 的第一个 坐标点
     *
     *
     *
     */


    int times =0;
    float size=0;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        int action = ev.getAction();

        switch (action){

            case MotionEvent.ACTION_DOWN:

                break;

            case MotionEvent.ACTION_MOVE:

                float curX = ev.getX();
                float curY = ev.getY();
                if (getScrollY()==0){

                    if (times == 0){

                        y = curY;
                    }

                    times++;

                    //滑动的 y 方向上的 距离
                    size = curY - y;
                    if (size*rate<=300){
                        sc.change(size*rate);
                        Log.e(">>>>>>"+y,">>>>>>>>>>"+(size*rate));
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                size =0 ;
                times =0;
                sc.resert();
                break;

        }

        return super.onTouchEvent(ev);
    }


    public interface Sc{

        public void change(float y);

        public void resert();

    }

}
