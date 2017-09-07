package com.example.administrator.myview.opengles.Cube_pk;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.example.administrator.myview.opengles.square_pk.Square;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Administrator on 2017/8/28.
 */

public class Cube_OpRendener implements GLSurfaceView.Renderer {

    Cube cube;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1f,0.5f,0.5f,1.0f);

        cube = new Cube();
        cube.onSurfaceCreated();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        GLES20.glViewport(0,0,width,height);

        //除了，三角形外 调用的方法
        cube.onSurfaceChanged(width,height);

    }

    @Override
    public void onDrawFrame(GL10 gl) {

        Log.e(">>>>>>>>>>>>",">>>>>>>>>>>");
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT|GLES20.GL_DEPTH_BUFFER_BIT);
//        triangle.onDrawFrame();
        cube.onDrawFrame();

    }
}
