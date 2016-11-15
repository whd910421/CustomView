package com.arirus.viewlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by whd910421 on 16/11/11.
 */

public class TopBar extends RelativeLayout {

    private Button mLeftButton, mRightButton;
    private TextView mTitle;

    private int mLeftTextColor;
    private Drawable mLeftButtonBackground;
    private String mLeftText;

    private int mRightTextColor;
    private Drawable mRightButtonBackground;
    private String mRightText;

    private int mTitleColor;
    private float mTitleTextSize;
    private String mTitleString;

    private LayoutParams mLeftParams, mTitlepParams, mRightParams;


    private topbarClickListener mListener = null;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(0xFFF59563);
        // 通过这个方法，将你在atts.xml中定义的declare-styleable
        // 的所有属性的值存储到TypedArray中
        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.TopBar);
        // 从TypedArray中取出对应的值来为要设置的属性赋值
        mLeftTextColor = ta.getColor(
                R.styleable.TopBar_leftTextColor, 0);
        mLeftButtonBackground = ta.getDrawable(
                R.styleable.TopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);

        mRightTextColor = ta.getColor(
                R.styleable.TopBar_rightTextColor, 0);
        mRightButtonBackground = ta.getDrawable(
                R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);

        mTitleTextSize = ta.getDimension(
                R.styleable.TopBar_titleTextSize, 10);
        mTitleColor = ta.getColor(
                R.styleable.TopBar_titleTextColor, 0);
        mTitleString = ta.getString(R.styleable.TopBar_title);

        // 获取完TypedArray的值后，一般要调用
        // recyle方法来避免重新创建的时候的错误
        ta.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitle = new TextView(context);

        // 为创建的组件元素赋值
        // 值就来源于我们在引用的xml文件中给对应属性的赋值
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftButtonBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightButtonBackground);
        mRightButton.setText(mRightText);

        mTitle.setText(mTitleString);
        mTitle.setTextColor(mTitleColor);
        mTitle.setTextSize(mTitleTextSize);
        mTitle.setGravity(Gravity.CENTER);

        // 为组件元素设置相应的布局元素
        mLeftParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        // 添加到ViewGroup
        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton, mRightParams);

        mTitlepParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        mTitlepParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitle, mTitlepParams);

        // 按钮的点击事件，不需要具体的实现，
        // 只需调用接口的方法，回调的时候，会有具体的实现
        mRightButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mListener == null) return;
                mListener.rightClick();
            }
        });

        mLeftButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("ddddd","ddddddd");
                if (mListener == null) return;
                mListener.leftClick();
            }
        });
    }


    public void setOnTopbarClickListener(topbarClickListener mListener) {
        this.mListener = mListener;
    }

    /**
     * 设置按钮的显示与否 通过id区分按钮，flag区分是否显示
     *
     * @param id   id
     * @param flag 是否显示
     */

    public void setButtonVisable(int id, boolean flag) {
        if (flag) {
            if (id == 0) {
                mLeftButton.setVisibility(View.VISIBLE);
            } else {
                mRightButton.setVisibility(View.VISIBLE);
            }
        } else {
            if (id == 0) {
                mLeftButton.setVisibility(View.GONE);
            } else {
                mRightButton.setVisibility(View.GONE);
            }
        }
    }

    // 接口对象，实现回调机制，在回调方法中
    // 通过映射的接口对象调用接口中的方法
    // 而不用去考虑如何实现，具体的实现由调用者去创建
    public interface topbarClickListener {
        // 左按钮点击事件
        void leftClick();
        // 右按钮点击事件
        void rightClick();
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        measureChildren(widthMeasureSpec, heightMeasureSpec);
//        //调用View类中默认的测量方法
//        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
//    }

//    private int measureWidth(int widthMeasureSpec)
//    {
//        int result = 0;
//        int specMode = MeasureSpec.getMode(widthMeasureSpec);
//        int specSize = MeasureSpec.getSize(widthMeasureSpec);
//
//        if (specMode == MeasureSpec.EXACTLY)
//            result = specSize;
//        else
//        {
//            result = 200;
//            if (specMode == MeasureSpec.AT_MOST)
//            {
//                result = Math.min(result, specSize);
//            }
//        }
//        Log.i("11111",String.valueOf(result));
//        return result;
//    }
//
//    private int measureHeight(int heightMeasureSpec )
//    {
//        int result = 0;
//        int specMode = MeasureSpec.getMode(heightMeasureSpec);
//        int specSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        if (specMode == MeasureSpec.EXACTLY)
//            result = specSize;
//        else
//        {
//            result = 200;
//            if (specMode == MeasureSpec.AT_MOST)
//            {
//                result = Math.min(result, specSize);
//            }
//        }
//        Log.i("222222",String.valueOf(result));
//        return result;
//    }

}
