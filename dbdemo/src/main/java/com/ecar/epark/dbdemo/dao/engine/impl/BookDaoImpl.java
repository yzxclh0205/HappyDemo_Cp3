package com.ecar.epark.dbdemo.dao.engine.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ecar.epark.dbdemo.dao.bean.Book;
import com.ecar.epark.dbdemo.dao.engine.BookDao;
import com.ecar.epark.dbdemo.dao.helper.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class BookDaoImpl implements BookDao{

    private final SQLiteDatabase db;

    public BookDaoImpl(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public long insert(Book book) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.TABLE_BOOK_ID,book.id);
        values.put(DBHelper.TABLE_BOOK_NAME,book.name);
        values.put(DBHelper.TABLE_BOOK_AUTHOR,book.author);
        return db.insert(DBHelper.TABLE_BOOK,null,values);
    }

    @Override
    public int delete(String id) {
        return db.delete(DBHelper.TABLE_BOOK,DBHelper.TABLE_BOOK_ID+"=?",new String[]{id});
    }

    @Override
    public int update(Book book) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.TABLE_BOOK_ID,book.id);
        values.put(DBHelper.TABLE_BOOK_NAME,book.name);
        values.put(DBHelper.TABLE_BOOK_AUTHOR,book.author);
        return db.update(DBHelper.TABLE_BOOK,values,DBHelper.TABLE_BOOK_ID+"=?",new String[]{book.id});
    }

    @Override
    public List<Book> findAll() {
        Cursor cursor = db.query(DBHelper.TABLE_BOOK, null, null, null, null, null, null);
        List<Book> books = null;
        if(cursor!=null){
            books = new ArrayList<>();
            while (cursor.moveToNext()){
                int columnIndex = cursor.getColumnIndex(DBHelper.TABLE_BOOK_NAME);
                String columnContent = cursor.getColumnName(columnIndex);
                Book book = new Book();
                book.name = columnContent;
                books.add(book);
            }
        }
        cursor.close();
        return books;
    }
}
