package com.example.administrator.myview.opengles;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.myview.opengles.ovel_pk.Oval_OpRendener;

/**
 * Created by Administrator on 2017/8/29.
 */

public class Oval_Gl_acty extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GLSurfaceView glSurfaceView;
        glSurfaceView = new GLSurfaceView(this);
        //opengl的版本
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setRenderer(new Oval_OpRendener());
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        setContentView(glSurfaceView);
    }
}
