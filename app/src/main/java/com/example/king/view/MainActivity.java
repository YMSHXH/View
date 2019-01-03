package com.example.king.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyView mMyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //mMyView = (MyView) findViewById(R.id.myView);
        //重绘
        //mMyView.invalidate();//重绘，直接触发onDraw()方法，只能在主线程通知重绘
        //mMyView.postInvalidate();//重绘，直接触发onDraw()方法，面试题，区别，子线程可以通知重绘
    }


}
