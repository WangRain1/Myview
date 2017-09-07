package com.example.administrator.myview.opengles.triangle_pk;

        import android.opengl.GLES20;
        import android.opengl.GLSurfaceView;
        import android.util.Log;

        import javax.microedition.khronos.egl.EGLConfig;
        import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Administrator on 2017/8/28.
 */

public class Tr_OpRendener implements GLSurfaceView.Renderer {

    Triangle triangle;
    TriangleColorFull colorFull;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //清空屏木的颜色
        GLES20.glClearColor(1f,0.5f,0.5f,1.0f);

        triangle = new Triangle();

        colorFull = new TriangleColorFull();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        //设置视口大小
        GLES20.glViewport(0,0,width,height);
        colorFull.change(width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        Log.e(">>>>>>>>>>>>",">>>>>>>>>>>");
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT|GLES20.GL_DEPTH_BUFFER_BIT);
//        triangle.onDrawFrame();

        colorFull.onDrawFrame();
    }
}
