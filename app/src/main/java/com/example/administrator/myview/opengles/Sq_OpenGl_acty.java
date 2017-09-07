package com.example.administrator.myview.opengles;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.myview.R;
import com.example.administrator.myview.opengles.square_pk.Sq_OpRendener;

/**
 * Created by Administrator on 2017/8/25.
 */

public class Sq_OpenGl_acty extends Activity {

    GLSurfaceView glSurfaceView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opengl_1);

        glSurfaceView = (GLSurfaceView) findViewById(R.id.gls);
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setRenderer(new Sq_OpRendener());
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

    }
}
