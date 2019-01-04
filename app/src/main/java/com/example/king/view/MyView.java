package com.example.king.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

    private Paint paint;
    private int circleX;
    private int circleY;
    private int radius = 100;//半径
    private int color = Color.RED;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

//    public MyView(Context context,  AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    /**
     * 初始化对象的方法
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs){
        //对自定义属性初始化
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyView);
        radius = typedArray.getDimensionPixelSize(R.styleable.MyView_radius,100);
        color = typedArray.getColor(R.styleable.MyView_color,Color.BLUE);

        //初始化后
        if (typedArray != null){
            typedArray.recycle();//回收资源
        }

        //定义画笔
        paint = new Paint();
        paint.setColor(color);//定义画笔颜色
        paint.setAntiAlias(true); //抗锯齿
        //paint.setStyle(Paint.Style.FILL);//实心圆
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
    }

    /**
     * 测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 尺寸改变
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 布局
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆
        canvas.drawCircle(circleX,circleY,radius,paint);
    }

    /**
     * 触摸事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                circleX = (int) event.getX();
                circleY = (int) event.getY();
                //重绘
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                circleX = (int) event.getX();
                circleY = (int) event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }
}
