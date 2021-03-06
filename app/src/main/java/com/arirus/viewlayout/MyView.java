package com.arirus.viewlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyView extends Button {
    Paint mBackgroundPaint ;
    Paint mLinePaint;

    LinearGradient mLinearGradient;

    Paint mTextPaint;
    String mString;

    public void setString(String s)
    {
        mString = s;
        invalidate();
    }

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.RED);

        mLinePaint = new Paint();
        mLinePaint.setColor(Color.BLACK);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.YELLOW);
        mTextPaint.setTextSize(50);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public MyView(Context context, AttributeSet attrs,
                  int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPaint(mBackgroundPaint);
        mLinePaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        canvas.drawLine(0,0,50,500,mLinePaint);
        mLinearGradient = new LinearGradient(0,0,getMeasuredWidth()/2,getMeasuredHeight()/2,new int []{Color.BLUE,Color.WHITE, Color.RED},null, Shader.TileMode.MIRROR);
        mBackgroundPaint.setShader(mLinearGradient);

//        canvas.drawText(mString,getMeasuredWidth()/2,getMeasuredHeight()/2,mTextPaint);
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
