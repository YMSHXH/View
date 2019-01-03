package com.example.king.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class CircleRoundImageView extends ImageView {
    public CircleRoundImageView(Context context) {
        super(context);
    }

    public CircleRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public CircleRoundImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }


    @Override
    protected void onDraw(Canvas canvas) {

        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        if (bitmap != null){
            //对bitmap进行圆角转换
            Bitmap nb = getRoundBitmap(bitmap);

            //绘制新的bitmap
            canvas.drawBitmap(nb,0,0,null);
        } else {
            super.onDraw(canvas);
        }
    }

    /**
     * 获取圆角
     * @param bitmap
     * @return
     */
    private Bitmap getRoundBitmap(Bitmap bitmap){

        //宽高缩放比
        float ws = getMeasuredWidth()/bitmap.getWidth();
        float hs = getMeasuredHeight()/bitmap.getHeight();

        //矩阵变换类
        Matrix matrix = new Matrix();
        matrix.setScale(ws,hs);

        //对bitmap进行变换
        Bitmap nbit = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);

        //最终输出的对象
        //创建一个色彩是8888的bitmap对象
        Bitmap target = Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(),Bitmap.Config.ARGB_8888);

        //画布 画笔
        Canvas canvas = new Canvas(target);
        Paint paint = new Paint();

        //创建一个圆角的图片
        RectF rectF = new RectF(0,0,getMeasuredWidth(),getMeasuredHeight());
        canvas.drawRoundRect(rectF,50,50,paint);

        //设置画笔的模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        //画原始的bitmap
        canvas.drawBitmap(nbit,0,0,paint);
        return target;
    }
}
