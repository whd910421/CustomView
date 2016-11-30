package com.arirus.viewlayout;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<ChatContenBean> mData;
    private Handler mHandler;
    private ChatListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        new Thread(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        }).start();
        mData = new ArrayList<>();
        mListView = (ListView) findViewById(R.id.activity_chat_list_listView);
        mAdapter = new ChatListAdapter(this,mData);
        mListView.setAdapter(mAdapter);

    }

    private class ChatListAdapter extends BaseAdapter
    {
        private Context mContext;
        private ArrayList<ChatContenBean> mData;

        public ChatListAdapter(Context context, ArrayList<ChatContenBean> data) {
            mContext = context;
            mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            ChatContenBean bean = ( ChatContenBean ) getItem(position);
            return bean.getType();
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ChatContenBean bean = ( ChatContenBean ) getItem(position);
            ViewHolder mHolder;
            if (convertView == null)
            {
                mHolder = new ViewHolder();
                if (getItemViewType(position) == 0) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.leftchatcell, null);
                    mHolder.mIcon = (ImageView) convertView.findViewById(R.id.chat_left_cell_image);
                    mHolder.mText = (TextView) convertView.findViewById(R.id.chat_left_cell_content);
                }
                else
                {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.rightchatcell, null);
                    mHolder.mIcon = (ImageView) convertView.findViewById(R.id.chat_left_cell_image);
                    mHolder.mText = (TextView) convertView.findViewById(R.id.chat_left_cell_content);
                }
                convertView.setTag(mHolder);
            }else
            {
                mHolder = ( ViewHolder ) convertView.getTag();
            }
            mHolder.mIcon.setBackgroundResource(bean.getDrawble());
            mHolder.mText.setText(String.valueOf(bean.getType()));


            return convertView;
        }

        private class ViewHolder
        {
            private ImageView mIcon;
            private TextView mText;
        }
    }


    private void initData()
    {
        for (int i = 0; i < 50; i++) {
            ChatContenBean bean = new ChatContenBean();
            bean.setName("www");
            bean.setDrawble(R.drawable.ic_back);
            bean.setType(i%2);
            bean.setText("cur "+ i);
            mData.add(bean);
        }
        mAdapter.notifyDataSetChanged();
    }
}


