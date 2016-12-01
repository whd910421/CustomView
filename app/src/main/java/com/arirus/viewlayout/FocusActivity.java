package com.arirus.viewlayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FocusActivity extends Activity {

    ListView mListView;
    FocusAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);

        mListView = new ListView(this);
        addContentView(mListView,new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("cur "+i);
        }
        mAdapter = new FocusAdapter(this,list);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.setCurrentItem(position);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private class FocusAdapter extends BaseAdapter
    {
        private Context mContext;
        private ArrayList<String> mList;
        private int mCurItem = 0;

        public FocusAdapter(Context context, ArrayList<String> list) {
            mContext = context;
            mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (mCurItem == position)
                return 0;
            else
                return 1;
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                LinearLayout layout = new LinearLayout(mContext);
                layout.setOrientation(LinearLayout.VERTICAL);
                View view;
                if (getItemViewType(position) == 0)
                {
                    view = addFoucusView();
                    layout.addView(view);
                }
                else
                {
                    view = addNormal();
                    layout.addView(view);
                }
                int count = ((ViewGroup)view).getChildCount();
                Log.i("ddddddd","dd "+ count);
                for (int i = 0; i < count; i++) {
                    View childView = ((ViewGroup)view).getChildAt(i);
                    if (childView instanceof TextView) {
                        viewHolder.mTextView = (TextView) childView;
                    }else if (childView instanceof ImageView)
                    {
                        viewHolder.mImageView = (ImageView) childView;
                    }
                }
                convertView = layout;
                convertView.setTag(viewHolder);
            }else{
                viewHolder =(ViewHolder) convertView.getTag();
            }
            if(mCurItem == position) {
                viewHolder.mImageView.setBackgroundResource(R.drawable.ic_launcher);
            }else
            {
                viewHolder.mImageView.setBackgroundResource(R.drawable.ic_more);
                viewHolder.mTextView.setText(mList.get(position));
            }


            return convertView;
        }

        private View addFoucusView()
        {
            LinearLayout linearLayout = new LinearLayout(mContext);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            ImageView imageView = new ImageView(mContext);
            imageView.setBackgroundResource(R.drawable.ic_launcher);
            imageView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(imageView);
            return linearLayout;
        }

        private View addNormal()
        {
            LinearLayout linearLayout = new LinearLayout(mContext);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            ImageView imageView = new ImageView(mContext);
            imageView.setBackgroundResource(R.drawable.ic_more);
            imageView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(imageView);

            TextView textView = new TextView(mContext);
            textView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(textView);

            linearLayout.setGravity(Gravity.CENTER);
            return linearLayout;
        }

        private void setCurrentItem(int pos)
        {
            mCurItem = pos;
        }

        private class ViewHolder
        {
            public TextView mTextView;
            public ImageView mImageView;
        }

    }
}
