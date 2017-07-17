package com.ecar.epark.dbdemo.dao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "demo.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_BOOK = "table_book";
    public static final String TABLE_BOOK_ID = "_id";
    public static final String TABLE_BOOK_NAME = "name";
    public static final String TABLE_BOOK_AUTHOR = "author";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_BOOK + " ("
                + TABLE_BOOK_ID + " varchar(50)"
                + TABLE_BOOK_NAME + " varchar(50)"
                + TABLE_BOOK_AUTHOR + " varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
