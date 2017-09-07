package com.example.administrator.myview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.myview.viewanimator.WaveLine;

/**
 * Created by Administrator on 2017/8/14.
 */

public class WaveActy extends Activity {

    WaveLine waveLine;

    ImageView head;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wave);

        head = (ImageView) findViewById(R.id.head);

        final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(160,160);

        params.gravity = Gravity.CENTER|Gravity.BOTTOM;

        waveLine = (WaveLine) findViewById(R.id.wave);

        waveLine.setListener(new WaveLine.UpDownListener() {
            @Override
            public void listen(float a) {
                Log.e("---","=======-="+(int) -a);
                params.setMargins(0,0,0, (int) -a+10);
                head.setLayoutParams(params);
            }
        });
    }
}
