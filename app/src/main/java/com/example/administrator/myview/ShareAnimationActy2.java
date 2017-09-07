package com.example.administrator.myview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/14.
 */

public class ShareAnimationActy2 extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share2);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Intent intent = new Intent("GONGXIANGDONGHUA");

        sendBroadcast(intent);

        return super.onKeyDown(keyCode, event);

    }
}
