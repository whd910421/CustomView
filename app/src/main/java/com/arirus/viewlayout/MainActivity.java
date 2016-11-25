package com.arirus.viewlayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

//        ArrayAdapter<xxx> adapter = new ArrayAdapter<>(this,R.layout.listview_textview_item,objs);

//        NewAdapter adapter = new NewAdapter(this,R.layout.listview_item, objs);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, " "+ position+ " "+ id,Toast.LENGTH_LONG).show();
//            }
//        });


        List<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();;
        for(int i =0; i < objs.size(); i++) {
            Map<String,Object> item = new HashMap<String,Object>();
            int picIndex = R.drawable.ic_back;
            if(i%2 == 1) picIndex = R.drawable.ic_more;
            item.put("image", picIndex);
            item.put("text", objs.get(i).toString());
            item.put("bcheck", i%2==0);
            mData.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                mData,
                R.layout.listview_item,
                new String[]{"image","text", "bcheck"},
                new int[]{R.id.list_view_item_pic,R.id.list_view_item_text, R.id.list_view_item_checkbox});

        listView.setAdapter(adapter);

    }

//    public class NewAdapter extends ArrayAdapter<xxx>
//    {
//        int resourceID;
//
//        public NewAdapter(Context context, int resource, List<xxx> objects) {
//            super(context, resource, objects);
//            resourceID = resource;
//        }
//
//        @NonNull
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            final xxx tmp = getItem(position);
//            View view = LayoutInflater.from(getContext()).inflate(resourceID,null);
//            TextView textView = (TextView) view.findViewById(R.id.list_view_item_text);
//            textView.setText(tmp.toString());
//            ImageView imageView = (ImageView) view.findViewById(R.id.list_view_item_pic);
//            int i = tmp.getI()%2;
//            if (i == 0)
//                imageView.setImageResource(R.drawable.ic_back);
//            else
//                imageView.setImageResource(R.drawable.ic_more);
//            return view;
//        }
//    }


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

        public int getI() {
            return i;
        }
    }
}
