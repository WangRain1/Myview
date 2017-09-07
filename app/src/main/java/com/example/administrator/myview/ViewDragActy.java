package com.example.administrator.myview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ViewDragActy extends Activity {


    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewdraghelp);


        imageView = (ImageView) findViewById(R.id.mn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewDragActy.this,"点击",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
