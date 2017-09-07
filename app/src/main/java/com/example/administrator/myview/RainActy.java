package com.example.administrator.myview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.myview.viewanimator.RainLayout;

/**
 * Created by Administrator on 2017/6/12.
 */
public class RainActy extends Activity {


    RainLayout rainLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rain);


        rainLayout = (RainLayout) findViewById(R.id.rain);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        rainLayout.remove();
    }
}
