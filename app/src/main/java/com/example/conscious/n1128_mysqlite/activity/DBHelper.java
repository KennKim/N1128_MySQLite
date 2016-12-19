package com.example.conscious.n1128_mysqlite.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.conscious.n1128_mysqlite.dao.MyLocation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by conscious on 2016-11-28.
 */

public class DBHelper extends SQLiteOpenHelper {

    private Context context;

    public static final String TABLE_mylocation = "mylocation";
    public static final String TABLE_yourlocation = "yourlocation";
    public static final String COL_loc_no = "loc_no";
    public static final String COL_loc_name = "loc_name";
    public static final String COL_loc_comment = "loc_comment";
    public static final String COL_loc_date = "loc_date";

    private static final String sqlCreate1 = "CREATE TABLE " + TABLE_mylocation + " (" + COL_loc_no + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_loc_name + " VARCHAR(20)," + COL_loc_comment + " VARCHAR(50)," + COL_loc_date + " VARCHAR(20))";
    private static final String sqlCreate2 = "CREATE TABLE " + TABLE_yourlocation + " (" + COL_loc_no + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_loc_name + " VARCHAR(20)," + COL_loc_comment + " VARCHAR(50))";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        StringBuffer sb = new StringBuffer();
//        sb.append(" CREATE TABLE mylocation ( ");
//        sb.append(" loc_no INTEGER PRIMARY KEY AUTOINCREMENT, ");
//        sb.append(" loc_name VARCHAR(20), ");
//        sb.append(" loc_comment VARCHAR(50))");

        db.execSQL(sqlCreate2);
        db.execSQL(sqlCreate1);
        Toast.makeText(context, "Table 생성완료", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("TAG", "Upgrading db from version" + oldVersion + " to" + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS tablename");
        onCreate(db);
        Toast.makeText(context, "버전이 올라갔습니다.", Toast.LENGTH_SHORT).show();

    }

    public void addMyLocation(MyLocation myLocation) {

        SQLiteDatabase db = getWritableDatabase();

//        String sql = "INSERT INTO mylocation (" + COL_loc_name + ", " + COL_loc_comment + ", " + COL_loc_date + ") VALUES ( ?, ?) ";

//        StringBuffer sb = new StringBuffer();
//        sb.append(" INSERT INTO mylocation ( ");
//        sb.append(" loc_name, loc_comment, loc) ");
//        sb.append(" VALUES ( ?, ? ) ");


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_loc_name, myLocation.getLoc_name());
        contentValues.put(COL_loc_comment, myLocation.getLoc_comment());
        contentValues.put(COL_loc_date, dateFormat.format(date));
        db.insert(TABLE_mylocation, null, contentValues);

//        long rowId = db.insert(TABLE_mylocation, null, contentValues);

//        db.execSQL(sb.toString(),
//                new Object[]{
//                        myLocation.getLoc_name(),
//                        myLocation.getLoc_comment()});
        Toast.makeText(context, "Insert 완료", Toast.LENGTH_SHORT).show();
    }


    public ArrayList<MyLocation> getMyLocationAll() {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_mylocation;
//        String sql = "SELECT " + COL_loc_no + ", " + COL_loc_name + ", " + COL_loc_comment + ", " + COL_loc_date + " FROM " + TABLE_mylocation;

        Cursor c = db.rawQuery(sql, null);

        ArrayList<MyLocation> myLocations = new ArrayList<MyLocation>();

        if (c.moveToFirst()) {
            do {
                MyLocation myLocation = new MyLocation();

                myLocation.setLoc_no(c.getInt(c.getColumnIndex(COL_loc_no)));
                myLocation.setLoc_name(c.getString(c.getColumnIndex(COL_loc_name)));
                myLocation.setLoc_comment(c.getString(c.getColumnIndex(COL_loc_comment)));
                myLocation.setLoc_date(c.getString(c.getColumnIndex(COL_loc_date)));
                myLocations.add(myLocation);
                Log.d("helper", c.getString(c.getColumnIndex(COL_loc_name)));
            }
            while (c.moveToNext());
        }

        Log.d("abc", "getMyLocationAll END");
        return myLocations;
    }

    public void addYourLocation(MyLocation myLocation) {

        SQLiteDatabase db = getWritableDatabase();

        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO yourlocation ( ");
        sb.append(" loc_name, loc_comment ) ");
        sb.append(" VALUES ( ?, ? ) ");

        db.execSQL(sb.toString(),
                new Object[]{
                        myLocation.getLoc_name(),
                        myLocation.getLoc_comment()});
        Toast.makeText(context, "Insert 완료 your", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<MyLocation> getYourLocationAll() {
        SQLiteDatabase db = getReadableDatabase();

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT * FROM yourlocation ");

        Cursor c = db.rawQuery(sb.toString(), null);

        ArrayList<MyLocation> myLocations = new ArrayList<MyLocation>();

        if (c.moveToFirst()) {
            do {
                MyLocation myLocation = new MyLocation();

                myLocation.setLoc_no(c.getInt(c.getColumnIndex(COL_loc_no)));
                myLocation.setLoc_name(c.getString(c.getColumnIndex(COL_loc_name)));
                myLocation.setLoc_comment(c.getString(c.getColumnIndex(COL_loc_comment)));
                myLocations.add(myLocation);
                Log.d("helper", c.getString(c.getColumnIndex(COL_loc_name)));

            }
            while (c.moveToNext());
        }
        return myLocations;
    }

    public void initialize() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " + TABLE_yourlocation);
        Toast.makeText(context, TABLE_yourlocation + "테이블 초기화 완료", Toast.LENGTH_SHORT).show();

    }

    public ArrayList<MyLocation> getLocItem(String locNo){
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_mylocation + " WHERE " + COL_loc_no + " = " + locNo;
//        String sql = "SELECT * FROM " + TABLE_mylocation + " WHERE " + COL_loc_no + " = " + locNo;
        Cursor c = db.rawQuery(sql, null);

        ArrayList<MyLocation> myLocations = new ArrayList<MyLocation>();

        if (c.moveToFirst()) {
            do {
                MyLocation myLocation = new MyLocation();
                myLocation.setLoc_no(c.getInt(c.getColumnIndex(COL_loc_no)));
                myLocation.setLoc_name(c.getString(c.getColumnIndex(COL_loc_name)));
                myLocation.setLoc_comment(c.getString(c.getColumnIndex(COL_loc_comment)));
                myLocation.setLoc_comment(c.getString(c.getColumnIndex(COL_loc_date)));
                myLocations.add(myLocation);
                Log.d("helper", c.getString(c.getColumnIndex(COL_loc_name)));
            }
            while (c.moveToNext());
        }
        return myLocations;

    }
}
