package com.example.administrator.myview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.myview.donghuaHelpclass.ShoppingCarLine;

/**
 * Created by Administrator on 2017/6/1.
 */

public class ChangeText extends Activity implements ShoppingCarLine.CallOnclick {

    ShoppingCarLine carLine;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_text);
        carLine = (ShoppingCarLine) findViewById(R.id.shop);
        carLine.setOnclick(this);


    }

    @Override
    public void click() {
        carLine.startAnimator();
    }
}
