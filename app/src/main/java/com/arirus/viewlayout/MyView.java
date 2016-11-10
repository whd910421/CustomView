package com.arirus.viewlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
    Paint mBackgroundPaint ;
    Paint mLinePaint;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.RED);

        mLinePaint = new Paint();
        mLinePaint.setColor(Color.BLACK);
    }

    public MyView(Context context, AttributeSet attrs,
                  int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawPaint(mBackgroundPaint);
        canvas.drawLine(0,0,50,500,mLinePaint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureLength(widthMeasureSpec), measureLength(heightMeasureSpec));
    }

    private int measureLength(int measureSpec)
    {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY)
            result = specSize;
        else
        {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST)
            {
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("xys", "View onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("xys", "View dispatchTouchEvent" + event.getAction());
        return super.dispatchTouchEvent(event);
    }

}
