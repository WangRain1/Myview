package com.example.administrator.myview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.administrator.myview.view.PopuView;
import com.example.administrator.myview.view.Progress80;

/**
 * Created by Administrator on 2017/5/26.
 */

public class DialogActy extends AppCompatActivity implements Progress80.AnimationIsComplete{
//    PopuView popuView;

    Animation animation;
    Progress80 progress80;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

//        popuView = (PopuView) findViewById(R.id.po);

        //错误提示
        progress80 = (Progress80) findViewById(R.id.pro);

        progress80.setIsComplete(this);
    }
    /**
     * 回调函数，调用动画
     */
    @Override
    public void isComplete() {
        animation= AnimationUtils.loadAnimation(this, R.anim.translate1);

        progress80.startAnimation(animation);
    }
}
