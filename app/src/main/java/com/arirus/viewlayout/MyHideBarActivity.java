package com.arirus.viewlayout;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyHideBarActivity extends Activity {
    private Toolbar mToolbar ;
    private int mTouchSlop;
    private ListView mListView;
    private float FirstYpos;
    private float CurYpos;
    private int mDirection;
    private ObjectAnimator mAnimator;
    private boolean mBShow;
    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                {
                    FirstYpos = event.getY();
                    break;
                }
                case  MotionEvent.ACTION_MOVE:
                {
                    CurYpos = event.getY();
                    if (CurYpos - FirstYpos>mTouchSlop)
                    {
                        mDirection = 1; //down
                        if (!mBShow)
                        {
                            SetToolBarAni();
                            mBShow = !mBShow;
                        }
                    }else if( FirstYpos - CurYpos>mTouchSlop)
                    {
                        mDirection = 0; //up
                        if (mBShow)
                        {
                            SetToolBarAni();
                            mBShow = !mBShow;
                        }
                    }

                    Log.i("DDDDDD", " "+ mDirection+ " "+ mBShow);
                    SetToolBarAni();
                    break;
                }
                case  MotionEvent.ACTION_UP:
                {
                    break;
                }
            }
            return false;
        }
    };

    private void SetToolBarAni()
    {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
        if (mDirection == 1) {//up
            mAnimator = ObjectAnimator.ofFloat(mToolbar,
                    "translationY", mToolbar.getTranslationY(), 0);
        } else { //down
            mAnimator = ObjectAnimator.ofFloat(mToolbar,
                    "translationY", mToolbar.getTranslationY(),
                    -mToolbar.getHeight());
        }
        mAnimator.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_hide_bar);
        mToolbar = (Toolbar)findViewById(R.id.activity_my_hide_bar_toolbar);
        mListView = (ListView)findViewById(R.id.activity_my_hide_bar_listview);
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();

        View header = new View(this);
        header.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.abc_action_bar_default_height_material)) );
        mListView.addHeaderView(header);
        List<String> mData = new ArrayList<>();
        for (int i = 1; i<100; i++)
        {
            mData.add("cur "+ i);
        }

        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mData));
        mListView.setOnTouchListener(mOnTouchListener);
    }
}
