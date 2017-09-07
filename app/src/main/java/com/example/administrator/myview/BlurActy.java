package com.example.administrator.myview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.example.administrator.myview.utils.BlurBitmap;
import com.example.administrator.myview.viewanimator.RoundScrollview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/8.
 */

public class BlurActy extends Activity {

    Bitmap bitmap;
    Bitmap bitmap2;
    ImageView imageView;
    ImageView alph;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blur);
        imageView = (ImageView) findViewById(R.id.blur);
        alph = (ImageView) findViewById(R.id.alph);

        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.timg);
        bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.timg);

//        RoundScrollview r = (RoundScrollview) findViewById(R.id.scroll);

        alph.setImageBitmap(bitmap2);
        Bitmap b = BlurBitmap.blur(this,bitmap);

        imageView.setImageBitmap(b);

        handler.sendEmptyMessageDelayed(1,1);

    }

    int va=0;
    boolean is = false;

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

                if (va>215){
                    is = true;
                }else if (va<10){
                    is = false;
                }

            if (msg.what==1){
                va -=5;
            }else {
                va +=5;
            }

            if (is){
                handler.sendEmptyMessageDelayed(1,10);
            }else {
                handler.sendEmptyMessageDelayed(2,10);
            }
            alph.setAlpha(va);

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (handler!=null){
            bitmap2.recycle();
            bitmap.recycle();
            handler.removeCallbacksAndMessages(null);
        }

    }

    /**
     * 高斯模糊
     * @param bitmap
     * @return
     */
    public Bitmap blurBitmap(Bitmap bitmap){

        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(getApplicationContext());
        //Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);
        //Set the radius of the blur: 0 < radius <= 25
        blurScript.setRadius(10.0f);
        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);
        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);
        //recycle the original bitmap
        bitmap.recycle();
        //After finishing everything, we destroy the Renderscript.
        rs.destroy();
        return outBitmap;
    }
}
