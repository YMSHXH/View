package com.example.king.view.area;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.king.view.R;
import com.example.king.view.bean.DotBean;
import com.example.king.view.search.SearchActivity;

import java.util.Random;

public class DotActivity extends AppCompatActivity implements View.OnClickListener {

    private DotView mDotView;
    /**
     * 添加
     */
    private Button mBtnAdd;
    /**
     * 删除
     */
    private Button mBtnDel;
    /**
     * 跳转
     */
    private Button mJump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot);
        initView();

    }

    /**
     * 添加随即点
     *
     */
    public void add() {
        DotBean dotBean = new DotBean();
        //获取控件的宽和高
        int width = mDotView.getWidth();
        int height = mDotView.getHeight();
        //添加数据
        dotBean.setX(new Random().nextInt(width));
        dotBean.setY(new Random().nextInt(height));
        dotBean.setIschecked(false);
        //Toast.makeText(this,dotBean+"====",Toast.LENGTH_SHORT).show();
        mDotView.add(dotBean);

    }

    /**
     * 删除随即点
     *
     */
    public void del() {
        //Toast.makeText(this,"====",Toast.LENGTH_SHORT).show();
        mDotView.del();
    }


    private void initView() {
        mDotView = findViewById(R.id.dotView);
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(this);
        mBtnDel = (Button) findViewById(R.id.btn_del);
        mBtnDel.setOnClickListener(this);
        mJump = (Button) findViewById(R.id.jump);
        mJump.setOnClickListener(this);
    }

    public void jump() {

        Intent intent = new Intent(DotActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_add:
                add();
                break;
            case R.id.btn_del:
                del();
                break;
            case R.id.jump:
                jump();
                break;
        }
    }
}
