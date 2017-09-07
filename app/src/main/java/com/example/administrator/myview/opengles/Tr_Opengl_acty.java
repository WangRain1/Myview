package com.example.administrator.myview.opengles;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.myview.opengles.triangle_pk.Tr_OpRendener;

/**
 * Created by Administrator on 2017/8/28.
 */

public class Tr_Opengl_acty extends Activity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView glSurfaceView;
        glSurfaceView = new GLSurfaceView(this);
        //opengl的版本
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setRenderer(new Tr_OpRendener());
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        setContentView(glSurfaceView);
    }
}
