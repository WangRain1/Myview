package com.example.administrator.myview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.myview.matrixs.Matrix3DView;
import com.example.administrator.myview.matrixs.Matrix3dReflectionView;

/**
 * Created by Administrator on 2017/8/30.
 */

public class Matrixacty1 extends Activity {

    RelativeLayout linearLayout;

    Matrix3dReflectionView matrix3dReflectionView;

    Matrix3DView matrix3DView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix1);


        matrix3dReflectionView = new Matrix3dReflectionView(this);
        matrix3DView = new Matrix3DView(this);

        linearLayout = (RelativeLayout) findViewById(R.id.lay);

        linearLayout.addView(matrix3dReflectionView);



    }

    boolean tiv = true;

    public void btn(View view){

        linearLayout.removeAllViews();
        if (tiv){

            linearLayout.addView(matrix3DView);
            tiv = false;
        }else {
            tiv = true;
            linearLayout.addView(matrix3dReflectionView);
        }
    }

}
