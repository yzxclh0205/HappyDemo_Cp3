package com.ecar.epark.happydemo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "happydemo.db";
    private final static int DB_VERSION = 1;

    //某个表的 字段，临时写在这
    public final static String TABLE_BOOK = "table_book";
    public final static String TABLE_BOOK_ID = "_id";
    public final static String TABLE_BOOK_NAME = "name";
    public final static String TABLE_BOOK_AUTHOR = "author";

    public final static String TABLE_DESK = "table_desk";
    public final static String TABLE_DESK_ID = "_id";
    public final static String TABLE_DESK_NAME = "desk_name";
    public final static String TABLE_DESK_PURPOSE = "desk_purpose";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_BOOK + " ("
                + TABLE_BOOK_ID + " varchar(50),"
                + TABLE_BOOK_NAME + " varchar(50),"
                + TABLE_BOOK_AUTHOR + " varchar(50))");

        db.execSQL("CREATE TABLE " + TABLE_DESK + " ("
                + TABLE_DESK_ID + " varchar(50),"
                + TABLE_DESK_NAME + " varchar(50),"
                + TABLE_DESK_PURPOSE + " varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
