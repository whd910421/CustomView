package com.arirus.viewlayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.activity_listview);
//        View view  = getLayoutInflater().inflate(R.layout.listview_item,null);
//        addContentView(view,new ViewGroup.LayoutParams(100, 100));
//        xxx[] objs = new xxx[]{new xxx(1),new xxx(2),new xxx(3),new xxx(4)};

        ArrayList<xxx> objs = new ArrayList<>();

        for (int i = 1; i<=100; i++)
        {
            objs.add(new xxx(i));
        }

        ArrayAdapter<xxx> adapter = new ArrayAdapter<xxx>(this,R.layout.listview_item,R.id.list_view_item_text, objs);
        listView.setAdapter(adapter);
    }

    public class NewAdapter extends ArrayAdapter<xxx>
    {
        int resourceID;

        public NewAdapter(Context context, int resource, List<xxx> objects) {
            super(context, resource, objects);
            resourceID = resource;
        }

        public NewAdapter(Context context, int resource, int textViewResourceId, List<xxx> objects) {
            super(context, resource, textViewResourceId, objects);
            resourceID = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            xxx tmp = getItem(position);
            View view = LayoutInflater.from(getContext()).inflate(resourceID,null);
            TextView textView = (TextView) view.findViewById(R.id.list_view_item_text);
            textView.setText(tmp.toString());
            return view;
        }
    }


    public class xxx
    {
        int i;

        public xxx(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return "xxx{" +
                    "i=" + i +
                    '}';
        }
    }
}
