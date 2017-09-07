package com.example.administrator.myview.viewanimator;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.example.administrator.myview.R;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ViewDraghelperDemo extends FrameLayout {

    ViewDragHelper dragHelper ;

    public ViewDraghelperDemo(@NonNull Context context) {
        this(context,null);
    }

    public ViewDraghelperDemo(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ViewDraghelperDemo(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        dragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {

            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {

                //取得左边界的坐标
                final int leftBound = getPaddingLeft();
                //取得右边界的坐标
                final int rightBound = getWidth() - child.getWidth() - leftBound;
                //这个地方的含义就是 如果left的值 在leftbound和rightBound之间 那么就返回left
                //如果left的值 比 leftbound还要小 那么就说明 超过了左边界 那我们只能返回给他左边界的值
                //如果left的值 比rightbound还要大 那么就说明 超过了右边界，那我们只能返回给他右边界的值
                return Math.min(Math.max(left, leftBound), rightBound);
            }

            //同上
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                final int topBound = getPaddingTop();
                final int bottomBound = getHeight() - child.getHeight() - topBound;
                return Math.min(Math.max(top, topBound), bottomBound);
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                //计算水平拖拽范围，让view 在屏幕内 拖拽
                if (child==mn){
                    return getWidth()-getPaddingLeft()-child.getWidth();
                }
                return super.getViewHorizontalDragRange(child);
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                //计算垂直拖拽范围，让view 在屏幕内 拖拽
                if (child == mn){
                    return getHeight() - getTop() - child.getHeight();
                }
                return super.getViewVerticalDragRange(child);
            }
        });
    }
    ImageView mn;
    /**
     * 布局加载完成后，运行这个方法，可以在这个方法中初始化一些 对象view
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mn = (ImageView) this.findViewById(R.id.mn);
    }
    /**
     * 下面这两个方法必须要增加，接管手势的
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }
}
