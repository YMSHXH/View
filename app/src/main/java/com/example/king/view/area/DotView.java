package com.example.king.view.area;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.king.view.bean.DotBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DotView extends View {

    private List<DotBean> list = new ArrayList<>();
    private Paint paint;
    private Paint rectPaint;
    private Paint checkPaint;
    private int left,right,top,bottom;

    public DotView(Context context) {
        this(context,null);
    }

    public DotView(Context context,AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DotView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public DotView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }


    /**
     * 初始化
     */
    private void init(){
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);

        //选择后
        checkPaint = new Paint();
        checkPaint.setColor(Color.RED);
        checkPaint.setAntiAlias(true);

        //矩形
        rectPaint = new Paint();
        rectPaint.setColor(Color.GREEN);
        rectPaint.setAntiAlias(true);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(10);
    }

    /**
     * 绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (list != null){
            for (DotBean dotBean : list) {
                if (dotBean.isIschecked()){
                    canvas.drawCircle(dotBean.getX(), dotBean.getY(), 20, checkPaint);
                } else {
                    canvas.drawCircle(dotBean.getX(), dotBean.getY(), 20, paint);
                }
            }
        }

        Rect rect = new Rect(left,top,right,bottom);
        canvas.drawRect(rect,rectPaint);
    }

    /**
     * 添加数据
     * @param dotBean
     */
    public void add(DotBean dotBean) {
        if (dotBean != null){
            list.add(dotBean);
            //重绘
            invalidate();
        }
    }

    /**
     * 删除圆点
     */
    public void del() {
//        for (DotBean d : list) {
//            if (d.isIschecked()){
//                list.remove(d);
//                invalidate();
//            }
//        }
        //迭代器删除
        Iterator<DotBean> it = list.iterator();
        while (it.hasNext()){
            DotBean bean = it.next();
            if (bean.isIschecked()){
                it.remove();
                invalidate();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                left = (int) event.getX();
                top = (int) event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                right = (int) event.getX();
                bottom = (int) event.getY();

                //对矩形区域内进行选择判断
                for (DotBean d : list) {
                    if (d.getX() >= left && d.getX() <= right && d.getY() >= top && d.getY() <= bottom){
                        d.setIschecked(true);
                    } else {
                        d.setIschecked(false);
                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

}
