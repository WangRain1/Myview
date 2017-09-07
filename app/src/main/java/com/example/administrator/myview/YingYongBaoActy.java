package com.example.administrator.myview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.administrator.myview.viewanimator.LauncherView;

/**
 * Created by Administrator on 2017/8/11.
 */

public class YingYongBaoActy extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yyb);
        final LauncherView launcherView = (LauncherView) findViewById(R.id.load_view);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcherView.start();
            }
        });
    }
}
