package com.example.administrator.myview.viewanimator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.administrator.myview.R;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by Administrator on 2017/6/29.
 */

public class ViewDragHelperMT extends RelativeLayout {

    View content;
    ViewDragHelper dragHelper;
    //获得宽高
    int w;
    int h;

    public ViewDragHelperMT(Context context) {
        this(context,null);
    }

    public ViewDragHelperMT(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ViewDragHelperMT(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//获得屏幕的宽高
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        w = wm.getDefaultDisplay().getWidth();
        h = wm.getDefaultDisplay().getHeight();
    }
    public void init(){

        dragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            /**
             * 判断当前哪些view是可以拖拽的
             * @param child
             * @param pointerId
             * @return
             */
            @Override
            public boolean tryCaptureView(View child, int pointerId) {

                return child == content;
            }

            /**
             * 真正控制 能滑动 的范围的方法
             * @param child
             * @param top
             * @param dy
             * @return
             */
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {

                int topb = getPaddingTop();
                //减去 100 是为了让view 不完全滑出 界面
                int bottomb = getHeight() - getPaddingTop() -100 ;

                return Math.min(Math.max(top,topb),bottomb);

            }

            @Override
            public int getViewVerticalDragRange(View child) {

                if (child == content){

                    return getHeight() - getPaddingTop();
                }
                return super.getViewVerticalDragRange(child);
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                //上推
                if (yvel<0){

                    if (getHeight()-releasedChild.getTop()>(getHeight()/5)){
                        dragHelper.smoothSlideViewTo(content,0,0);
                    }else {
                        dragHelper.smoothSlideViewTo(content,0,getHeight() - getPaddingTop() -400 );
                    }
                }else if (yvel>0){
                    //下拉
                    if ((getHeight()-releasedChild.getTop())>(getHeight()/5)){
                        dragHelper.smoothSlideViewTo(content,0,getHeight() - getPaddingTop() -400 );
                    }else {
                        dragHelper.smoothSlideViewTo(content,0,0);
                    }
                }
                invalidate();
            }



            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);

                ViewHelper.setPivotX(content,w/2);
                ViewHelper.setPivotY(content,h/2);
                float rate =1 - (float)top/getHeight();
                if (rate>0.9){

                    ViewHelper.setScaleX(content,rate);
                }else {
                    ViewHelper.setScaleX(content,0.9f);
                }

                ViewHelper.setScaleX(image_cover,rate);
                ViewHelper.setScaleY(image_cover,rate);
            }
        });
        //拖拽边缘检测
        dragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_ALL);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //拖拽的时候 mViewDragHelper.continueSettling(true) 此方法 返回 false  ， 放手的时候返回 true
        if (dragHelper.continueSettling(true)) {
//            postInvalidateOnAnimation();
            postInvalidate();
        }
    }
    MarginLayoutParams marginLayoutParams;
    FrameLayout wai;
    ImageView image_cover;

    ListView list;
    int currentItem;

    /**
     * 初始进入后----就修改状态是弹开 的
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        dragHelper.smoothSlideViewTo(content,0,getHeight() - getPaddingTop() -400 );
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        content  = LayoutInflater.from(getContext()).inflate(R.layout.dragview,this,false);
        wai = (FrameLayout) content.findViewById(R.id.wai);
        image_cover = (ImageView) content.findViewById(R.id.image_cover);
        list = (ListView) content.findViewById(R.id.list);
        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.e("firstVisibleItem--"+firstVisibleItem,"visibleItemCount----"+visibleItemCount);

                currentItem = firstVisibleItem;

            }
        });

        marginLayoutParams = new MarginLayoutParams(content.getLayoutParams());

        LayoutParams params = new LayoutParams(marginLayoutParams);
        params.height = ViewPager.LayoutParams.MATCH_PARENT;
        params.width = ViewPager.LayoutParams.MATCH_PARENT;
//        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        addView(content,params);

        init();
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        final int action = MotionEventCompat.getActionMasked(ev);

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {

            dragHelper.cancel();

            return false;
        }
        if (currentItem == 0){

        }else {
            return false;
        }

        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        dragHelper.processTouchEvent(event);

        return true;
    }
}
