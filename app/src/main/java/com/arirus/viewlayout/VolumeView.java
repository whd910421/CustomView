package com.arirus.viewlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by whd910421 on 16/11/18.
 */

public class VolumeView extends View {

    private static int sCount = 9;
    private Paint mRactPaint;
    private static int sOffset = 5;
    private static float sHight ;
    private static float sWitdh;
    private int mHeight;
    private int mWidth;

    private Paint mLinePaint;

    public VolumeView(Context context) {
        super(context);
    }

    public VolumeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VolumeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView()
    {
        mRactPaint = new Paint();
        mRactPaint.setColor(Color.RED);

        mRactPaint.setShader(new LinearGradient(
                0,
                0,
                sWitdh,
                sHight,
                Color.YELLOW,
                Color.BLUE,
                Shader.TileMode.REPEAT));

        mLinePaint = new Paint();
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mHeight = MeasureSpec.getSize( heightMeasureSpec);
        mWidth = MeasureSpec.getSize( widthMeasureSpec);
        sHight = (float) 0.7 *mHeight;
        sWitdh = (float) 0.1 * mWidth;

        setMeasuredDimension(mWidth,mHeight);
        initView();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i =1; i<10;i++) {
            canvas.drawRect(sOffset*i+(i-1)*sWitdh, (float) (sHight/5),sOffset*i+i*sWitdh,mHeight,mRactPaint);
        }
        canvas.drawLine(0,0,sWitdh,sHight,mLinePaint);

        postInvalidateDelayed(300);
//        invalidate();
    }
}
