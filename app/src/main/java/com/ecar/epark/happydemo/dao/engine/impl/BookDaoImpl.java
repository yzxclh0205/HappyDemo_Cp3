package com.ecar.epark.happydemo.dao.engine.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ecar.epark.happydemo.dao.DBHelper;
import com.ecar.epark.happydemo.dao.bean.Book;
import com.ecar.epark.happydemo.dao.engine.BookDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class BookDaoImpl implements BookDao {

    public Context context;
    private final SQLiteDatabase db;

    public BookDaoImpl(Context context) {
        this.context = context;
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    @Override
    public long insert(Book book) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.TABLE_BOOK_ID, book.id);
        values.put(DBHelper.TABLE_BOOK_NAME, book.name);
        values.put(DBHelper.TABLE_BOOK_AUTHOR, book.author);
        long insertIndex = db.insert(DBHelper.TABLE_BOOK, null, values);
        return insertIndex;
    }

    @Override
    public int delete(String id) {
        int deleteNum = db.delete(DBHelper.TABLE_BOOK, DBHelper.TABLE_BOOK_ID + "=?", new String[]{id});
        return deleteNum;
    }

    @Override
    public int update(Book book) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.TABLE_BOOK_NAME, book.name);
        values.put(DBHelper.TABLE_BOOK_AUTHOR, book.author);
        int updateNum = db.update(DBHelper.TABLE_BOOK, values, DBHelper.TABLE_BOOK_ID.concat("=?"), new String[]{book.id});
        return updateNum;
    }

    @Override
    public List<Book> findAll() {
        Cursor cursor = db.query(DBHelper.TABLE_BOOK, null, null, null, null, null, null);
        List<Book> books = null;
        if (cursor != null) {
            books = new ArrayList<>();
            while (cursor.moveToNext()) {
                int nameIndex = cursor.getColumnIndex(DBHelper.TABLE_BOOK_NAME);
                String booName = cursor.getString(nameIndex);
                Book book = new Book();
                book.name = booName;
                books.add(book);
            }
        }
        cursor.close();
        return books;
    }
}
