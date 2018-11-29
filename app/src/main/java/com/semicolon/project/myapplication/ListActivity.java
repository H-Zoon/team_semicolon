package com.semicolon.project.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {

    DBManager db;
    ListView lv;
    ArrayAdapter<HashMap<String,String>> adapter;
    HashMap<String,String> hashMap=null;

    ArrayList <HashMap<String,String>> theList = new ArrayList<>();
    static final String TAG_NAME="NAME";
    static final String TAG_MEMO="MEMO";
    static final String TAG_DATE="DATE";
    static final String TAG_VALUE="VALUE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = new DBManager(this);

        Cursor data = db.getListContents();

        if (data.getCount() == 0) {
            Toast.makeText(ListActivity.this, "저장된 상품이 없습니다", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                hashMap= new HashMap<String, String>();

                hashMap.put(TAG_NAME,data.getString(1));
                hashMap.put(TAG_MEMO,data.getString(2));
                hashMap.put(TAG_DATE,data.getString(3));
                hashMap.put(TAG_VALUE,data.getString(4));

                theList.add(hashMap);
            }
        }

        lv = (ListView) findViewById(R.id.listview);
        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, theList);
        SimpleAdapter simpleAdapter= new SimpleAdapter(this,theList,R.layout.list_item,new String[]{TAG_NAME,TAG_MEMO,TAG_DATE,TAG_VALUE},
                new int[]{R.id.NAME,R.id.MEMO,R.id.DATE,R.id.VALUE});
        lv.setAdapter(simpleAdapter);

    }

}

