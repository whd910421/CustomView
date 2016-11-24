package com.arirus.viewlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by whd910421 on 16/11/10.
 */

public class CustomTextView extends TextView {
    private Paint mInnerPaint;
    private Paint mOuterPaint;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onDraw(Canvas canvas) {

        mInnerPaint = new Paint();
        mOuterPaint = new Paint();

        mInnerPaint.setColor(Color.CYAN);
        mInnerPaint.setStyle(Paint.Style.FILL);

        mOuterPaint.setColor(Color.YELLOW);
        mOuterPaint.setStyle(Paint.Style.FILL);

        canvas.drawRect(0,0,getMeasuredWidth()+10,getMeasuredHeight()+10,mInnerPaint);
        canvas.drawRect(10,10,getMeasuredWidth(),getMeasuredHeight(),mOuterPaint);

        canvas.save();
//        canvas.translate(100,0);
        super.onDraw(canvas);
        canvas.restore();
    }
}
