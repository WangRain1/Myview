package com.example.administrator.myview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.administrator.myview.donghuaSVG.PathView;

/**
 * Created by Administrator on 2017/6/6.
 */

public class SVGacty extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.svg);

        final PathView pathView = (PathView) findViewById(R.id.pathView);
//        final Path path = makeConvexArrow(50, 100);
//        pathView.setPath(path);
        pathView.setFillAfter(true);
        pathView.useNaturalColors();
        pathView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pathView.getPathAnimator().
                        delay(100).
                        duration(1500).
                        interpolator(new AccelerateDecelerateInterpolator()).
                        start();
            }
        });







    }
}
