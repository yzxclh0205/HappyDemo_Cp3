package com.ecar.epark.happydemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ecar.epark.happydemo.dao.DBHelper;
import com.ecar.epark.happydemo.dao.bean.Book;
import com.ecar.epark.happydemo.dao.engine.BookDao;
import com.ecar.epark.happydemo.dao.engine.impl.BookDaoImpl;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
//
//        assertEquals("com.ecar.epark.happydemo","com.ecar.epark.happydemo");// appContext.getPackageName());
//        System.out.println("4");
        DBHelper helper = new DBHelper(appContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        BookDao bookDao = new BookDaoImpl(appContext);
        Book book = new Book();
        book.id ="id1";
        book.name = "name1";
        book.author = "author1";
        bookDao.insert(book);
    }

    @Test
    public void insert() throws Exception{

    }
}
