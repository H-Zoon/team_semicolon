package com.semicolon.project.myapplication;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.widget.Toast;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

//DB를 총괄관리
public class DBManager extends SQLiteOpenHelper {

    // DB관련 상수 선언
    private static final String dbName = "APinfo.db";
    private static final String tableName = "APinfo";
    public static final int dbVersion = 1;
    private static final String COL1 = "ID";
    private static final String COL2 = "NAME";

    // DB관련 객체 선언
    private SQLiteDatabase db; // DB controller

    public DBManager(Context context) {
        super(context,dbName, null, dbVersion);
        // TODO Auto-generated constructor stub
        }

        // 생성된 DB가 없을 경우에 한번만 호출됨
        public void onCreate(SQLiteDatabase db) {
            // String dropSql = "drop table if exists " + tableName;
            // db.execSQL(dropSql);

            String createSql = " CREATE TABLE " + tableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "NAME TEXT)";

           // db.execSQL("CREATE TABLE IF NOT EXISTS APinfo (id text PRIMARY KEY AUTOINCREMENT," + " Name text);");
            db.execSQL(createSql);
        }


        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            // TODO Auto-generated method stub
        }

        public void addData(String item1) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL2, item1);

            long result = db.insert(tableName, null, contentValues);

        }

        public Cursor getListContents() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor data = db.rawQuery("SELECT * FROM " + tableName, null);
            return data;
        }

        public void deleteData(){
            String sql = "delete from " + "APinfo" + ";";
            db.execSQL(sql);
        }
}
/*
    // 데이터 추가
    public void insertData(String Name) {

        ContentValues values = new ContentValues();
        values.put("Name", Name);

        long result = db.insert(tableName, null, values);
    }

    public Cursor selectAll() {
        String sql = "select * from " + tableName + ";";
        Cursor results = db.rawQuery(sql, null);

        return results;
    }
}

/*
    // 데이터 삭제(안돌아가면 id 삽입)
    public void removeData() {
        String sql = "delete from " + "APinfo" + ";";
        db.execSQL(sql);
    }
/*
    public Cursor selectData() {
        String sql = "select * from " + tableName + ";";
        Cursor cursor = db.rawQuery(sql, null);

        // result(Cursor 객체)가 비어 있으면 false 리턴
        while (cursor.moveToFirst()) {
            String name = cursor.getString(0);
        }
        cursor.close();
        return null;
    }
/*
     public void updateData(APinfo info, int index) {
        String sql = "update " + tableName + " set SSID = '" + info.getSSID()
                + "', capabilities = " + info.getCapabilities()
                + ", passwd = '" + info.getPasswd() + "' where id = " + index
                + ";";
        db.execSQL(sql);
    }


    // 데이터 전체 검색
    public Cursor selectAll() {
        String sql = "select * from " + tableName + ";";
        Cursor results = db.rawQuery(sql, null);

        results.moveToFirst();
        ArrayList<apinfo> infos = new ArrayList<apinfo>();

        while (!results.isAfterLast()) {
            APinfo info = new APinfo(results.getInt(0), results.getString(1),
                    results.getInt(2), results.getString(3));
            infos.add(info);
            results.moveToNext();
        }
        results.close();
        return infos;
    }*/


