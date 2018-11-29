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
public class Tip_DB extends SQLiteOpenHelper {
    // DB관련 상수 선언
    private static final String dbName = "tipInfo.db";
    private static final String tableName = "tipInfo";
    public static final int dbVersion = 1;
    private static final String COL1 = "ID";
    private static final String COL2 = "Tip_String";

    // DB관련 객체 선언
    private SQLiteDatabase db; // DB controller

    public Tip_DB(Context context, String Tip) {
        super(context,dbName, null, dbVersion);
        // TODO Auto-generated constructor stub
    }

    // 생성된 DB가 없을 경우에 한번만 호출됨
    public void onCreate(SQLiteDatabase db) {
        // String dropSql = "drop table if exists " + tableName;
        // db.execSQL(dropSql);

        String createSql = " CREATE TABLE " + tableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "Tip_String)";

        // db.execSQL("CREATE TABLE IF NOT EXISTS APinfo (id text PRIMARY KEY AUTOINCREMENT," + " Name text);");
        db.execSQL(createSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }

    public void addData(String item1,String item2,String item3) {
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
        String sql = "delete from " + "tipInfo" + ";";
        db.execSQL(sql);
    }
}
