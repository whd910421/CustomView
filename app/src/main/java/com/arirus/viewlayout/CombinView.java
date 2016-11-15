package com.arirus.viewlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by whd910421 on 16/11/15.
 */

public class CombinView extends View {
    private int mMeasureWidth;
    private int mMeasureHeigh;

    private float mRadius;
    private Paint mCriclePaint ;

    public CombinView(Context context) {
        super(context);
    }

    public CombinView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CombinView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取当前父窗口的大小
        mMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);
        mMeasureHeigh = MeasureSpec.getSize(heightMeasureSpec);
        Log.i("ssssssss",String.valueOf(mMeasureWidth));
        Log.i("ssssssss1",String.valueOf(mMeasureHeigh));
        Log.i("ssssssss2",String.valueOf(getDefaultSize(getSuggestedMinimumWidth(), mMeasureWidth)));
        Log.i("ssssssss2",String.valueOf(getDefaultSize(getSuggestedMinimumHeight(), mMeasureHeigh)));
        setMeasuredDimension(mMeasureWidth,mMeasureHeigh);

        mRadius = (float) 0.5 * Math.min(mMeasureHeigh,mMeasureWidth);
        mCriclePaint = new Paint();
        mCriclePaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mMeasureWidth/2, mMeasureHeigh/2, mRadius,mCriclePaint);
    }
}
