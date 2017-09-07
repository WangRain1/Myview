package com.example.administrator.myview;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.myview.opengles.Cube_Gl_acty;
import com.example.administrator.myview.opengles.Cube_pk.Cube;
import com.example.administrator.myview.opengles.Oval_Gl_acty;
import com.example.administrator.myview.opengles.Sq_OpenGl_acty;
import com.example.administrator.myview.opengles.Tr_Opengl_acty;
import com.example.administrator.myview.viewanimator.MyS;

public class MainActivity extends AppCompatActivity implements MyS.Sc {


    ImageView img;

    int imgWidth;
    int imgHeight;

    int times =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.img);
        img.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (times==0){
                    imgWidth = img.getMeasuredWidth();
                    imgHeight = img.getMeasuredHeight();
                    Log.e(">>>>>>","---------"+imgHeight);
                    times++;
                }
            }
        });

        MyS myS  = (MyS) findViewById(R.id.sc);

        myS.setSc(this);
    }

    public void chart(View view){

        Intent s = new Intent(this,ChartActy.class);

        startActivity(s);
    }

    public void dialog(View view){
        Intent s = new Intent(this,DialogActy.class);

        startActivity(s);
    }

    public void bsp(View view){
        Intent s = new Intent(this,BeiSaiPathActy.class);

        startActivity(s);
    }

    public void animatorcicle(View view){
        Intent s = new Intent(this,AnimatorCicleActy.class);

        startActivity(s);
    }


    public void change_text(View view){
        Intent s = new Intent(this,ChangeText.class);

        startActivity(s);
    }
    public void searchview(View view){
        Intent s = new Intent(this,MySearchViewActy.class);

        startActivity(s);
    }

    public void svg(View view){
        Intent s = new Intent(this,SVGacty.class);

        startActivity(s);
    }
    public void blur(View view){
        Intent s = new Intent(this,BlurActy.class);

        startActivity(s);
    }

    public void heart(View view){

        Intent s = new Intent(this,CicleLoadingActy.class);

        startActivity(s);

    }

    public void rain(View view){

        Intent s = new Intent(this,RainActy.class);

        startActivity(s);

    }

    public void share(View view){

        Intent s = new Intent(this,ShareAimationActy1.class);

        startActivity(s);

    }

    public void didi(View view){

        Intent s = new Intent(this,DDActy.class);

        startActivity(s);

    }

    public void love(View view){

        Intent s = new Intent(this,LoveActy.class);

        startActivity(s);

    }

    public void drag(View view){

        Intent s = new Intent(this,ViewDragActy.class);

        startActivity(s);

    }

    public void drag2(View view){

        Intent intent = new Intent(this,ViewDragActy2.class);

        startActivity(intent);
    }

    public void power(View view){

        Intent intent = new Intent(this,PowerActy.class);

        startActivity(intent);
    }

    public void xformode(View view){

        Intent intent = new Intent(this,XformodeActy.class);

        startActivity(intent);
    }

    public void qx(View view){

        Intent intent = new Intent(this,QuxianActy.class);

        startActivity(intent);
    }

    public void yyb(View view){

        Intent s = new Intent(this,YingYongBaoActy.class);

        startActivity(s);

    }
    public void wave(View view){

        Intent s = new Intent(this,WaveActy.class);

        startActivity(s);

    }

    public void matrix(View view){

        Intent s = new Intent(this,Sq_OpenGl_acty.class);

        startActivity(s);

    }


    public void gl_tr(View view){

        Intent s = new Intent(this,Tr_Opengl_acty.class);

        startActivity(s);

    }

    public void gl_cube(View view){

        Intent s = new Intent(this,Cube_Gl_acty.class);

        startActivity(s);

    }

    public void gl_oval(View view){

        Intent s = new Intent(this,Oval_Gl_acty.class);

        startActivity(s);

    }

    public void matrix1(View view){

        Intent s = new Intent(this,Matrixacty1.class);

        startActivity(s);

    }

    public void recycler(View view){

        Intent s = new Intent(this,RcycleActy.class);

        startActivity(s);

    }

    @Override
    public void change(float y) {

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) img.getLayoutParams();

        params.height =imgHeight +(int)y;
        params.width = imgWidth+(int)y;
        params.gravity = Gravity.CENTER;
        img.setLayoutParams(params);

    }

    /**
     * 还原 图片宽高
     */
    @Override
    public void resert() {
        scaleHeight();
        scaleWidth();
    }


    public void scaleHeight(){

        ValueAnimator animator = ValueAnimator.ofInt(img.getMeasuredHeight(),imgHeight);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int yc = (int) animation.getAnimatedValue();

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) img.getLayoutParams();

                params.height = yc;
                params.gravity = Gravity.CENTER;
                img.setLayoutParams(params);
            }
        });
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();

    }

    public void scaleWidth(){

        ValueAnimator animator = ValueAnimator.ofInt(img.getMeasuredWidth(),imgWidth);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int yc = (int) animation.getAnimatedValue();
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) img.getLayoutParams();

                params.width = yc;
                params.gravity = Gravity.CENTER;
                img.setLayoutParams(params);
            }
        });
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();

    }
}
