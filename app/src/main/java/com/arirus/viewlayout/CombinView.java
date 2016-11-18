package com.arirus.viewlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.Window;

/**
 * Created by whd910421 on 16/11/15.
 */

public class CombinView extends View {
    private int mMeasureHeigth;
    private int mMeasureWidth;

    private Paint mCirclePaint;
    private float mCircleXY;
    private float mRadius;

    private Paint mArcPaint;
    private RectF mArcRectF;
    private float mSweepAngle;
    private float mSweepValue = 66;

    private Paint mTextPaint;
    private String mShowText;
    private float mShowTextSize;

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
        mMeasureHeigth = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(mMeasureWidth,mMeasureHeigth);

        float shortlength = 0;
        float longlength = 0;
        if (mMeasureHeigth >= mMeasureWidth) {
            shortlength = mMeasureWidth;
            longlength = mMeasureHeigth;
        } else {
            shortlength = mMeasureHeigth;
            longlength = mMeasureWidth;
        }

        mCircleXY = shortlength / 2;
        mRadius = (float) (shortlength * 0.5 / 2);
        mCirclePaint = new Paint();
//        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(getResources().getColor(
                android.R.color.holo_blue_bright));
        float dis = longlength- shortlength;
        Log.i("ddddddd",String.valueOf(dis));
        mArcRectF = new RectF(
                (float) (shortlength * 0.1),
                (float) (shortlength * 0.1),
                (float) (shortlength * 0.9),
                (float) (shortlength * 0.9));
        mSweepAngle = (mSweepValue / 100f) * 360f;
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(getResources().getColor(
                android.R.color.holo_blue_bright));
        mArcPaint.setStrokeWidth((float) (shortlength * 0.1));
        mArcPaint.setStyle(Paint.Style.STROKE);

        mShowText = setShowText();
        mShowTextSize = setShowTextSize();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mShowTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    private float setShowTextSize() {
        this.invalidate();
        return 50;
    }

    private String setShowText() {
        this.invalidate();
        return "Android Skill";
    }

    public void forceInvalidate() {
        this.invalidate();
    }

    public void setSweepValue(float sweepValue) {
        if (sweepValue != 0) {
            mSweepValue = sweepValue;
        } else {
            mSweepValue = 25;
        }
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制圆
//        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
        // 绘制弧线
        canvas.drawArc(mArcRectF, -90, 240, false, mArcPaint);
        // 绘制文字
        canvas.drawText(mShowText, 0, mShowText.length(),
                mCircleXY, mCircleXY + (mShowTextSize / 4), mTextPaint);
    }
}
