package com.semicolon.project.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    DBManager db;
    ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<String> theList = new ArrayList<>();


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
                theList.add(data.getString(1));

            }
        }

        lv = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, theList);
        lv.setAdapter(adapter);

    }


}

