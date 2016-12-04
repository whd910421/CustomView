package com.arirus.viewlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by whd on 2016/12/1.
 */

public class SimpleDragView extends View {
    private static final String TAG = "SimpleDragView";
    private int mLastL;
    private int mLastT;
    private int mLastR;
    private int mLastB;

    private int mLastX;
    private int mLastY;

    public SimpleDragView(Context context) {
        super(context);
    }

    public SimpleDragView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int xpos = (int)event.getX();
        int ypos = (int)event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                mLastX = xpos;
                mLastY = ypos;
                Log.i(TAG, " Down "+ mLastX);

                break;
            }
            case MotionEvent.ACTION_MOVE:
            {
                int xoffset = xpos - mLastX;
                int yoffset = ypos - mLastY;

                ((View)getParent()).scrollBy(-xoffset,-yoffset);

//                mLastX = xpos;
//                mLastY = ypos;
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                break;
            }
        }
        return true;
    }
}
