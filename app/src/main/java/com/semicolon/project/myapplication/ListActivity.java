package com.semicolon.project.myapplication;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {

    DBManager db;
    ArrayAdapter<HashMap<String,String>> adapter;
    HashMap<String,String> hashMap=null;

    ArrayList <HashMap<String,String>> theList = new ArrayList<>();
    static final String TAG_ID= "ID";
    static final String TAG_NAME="NAME";
    static final String TAG_MEMO="MEMO";
    static final String TAG_DATE="DATE";
    static final String TAG_VALUE="VALUE";
    static SimpleAdapter simpleAdapter;
    static Integer cursor_integer = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = new DBManager(this);
        sort_List();

        final ListView lv = (ListView) findViewById(R.id.listview);
        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, theList);
        simpleAdapter= new SimpleAdapter(this,theList,R.layout.list_item,new String[]{TAG_NAME,TAG_MEMO,TAG_DATE,TAG_VALUE},
                new int[]{R.id.NAME,R.id.MEMO,R.id.DATE,R.id.VALUE});
        lv.setAdapter(simpleAdapter);

        //리스트 터치
        lv.setClickable ( true );
        lv.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView <?> parent , View view , int position , long id) {
               HashMap selection = (HashMap)lv.getItemAtPosition ( position );
                final String d_name=(String)selection.get(TAG_NAME);

                Toast.makeText(getApplicationContext(), d_name, Toast.LENGTH_LONG).show();

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder (ListActivity.this);
                alertDialog.setTitle ( "삭제" )
                        .setMessage ( "삭제하시겠습니까?" )
                        .setPositiveButton ( "네" , new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialog , int which) {
                                //please(d_name);
                                db.deleteData (d_name);
                                sort_List();
                                simpleAdapter.notifyDataSetChanged();
                                Toast.makeText ( getApplicationContext (),"삭제되었습니다.",Toast.LENGTH_LONG ).show ();
                            }
                        });
                alertDialog.setNegativeButton ( "아니요" , new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog , int which) {
                        dialog.cancel ();
                    }
                } );
                AlertDialog al = alertDialog.create ();
                al.show ();
            }
        } );

        //끝

        Button button_sort = (Button) findViewById(R.id.button);

        button_sort.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cursor_integer = 1;
                sort_List();
                simpleAdapter.notifyDataSetChanged();
            }
        });

    }

    public void please(String name) {
        db.deleteData (name);
    }

    public void sort_List() {
        theList.clear();

        Cursor data;

        switch (cursor_integer) {
            case 0 :
                data = db.getListContents();
                break;
            case 1 :
                data = db.sort_Date();
                break;
            default:
                data = db.getListContents();
        }

        if (data.getCount() == 0) {
            Toast.makeText(ListActivity.this, "저장된 상품이 없습니다", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                hashMap = new HashMap<String, String>();

                hashMap.put(TAG_NAME, data.getString(1));
                hashMap.put(TAG_MEMO, data.getString(2));
                hashMap.put(TAG_DATE, data.getString(3));
                hashMap.put(TAG_VALUE, data.getString(4));

                theList.add(hashMap);
            }
        }
    }
}
