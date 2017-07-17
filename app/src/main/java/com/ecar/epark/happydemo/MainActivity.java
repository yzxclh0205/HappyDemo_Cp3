package com.ecar.epark.happydemo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.ecar.epark.happydemo.dao.DBHelper;
import com.ecar.epark.happydemo.dao.bean.Book;
import com.ecar.epark.happydemo.dao.bean.Desk;
import com.ecar.epark.happydemo.dao.engine.BookDao;
import com.ecar.epark.happydemo.dao.engine.impl.BookDaoImpl;
import com.ecar.epark.happydemo.dao.engine.impl.DeskImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        BookDao bookDao = new BookDaoImpl(this);
//        Book book = new Book();
//        book.id ="id1";
//        book.name = "name1";
//        book.author = "author1";
//        bookDao.insert(book);
        DeskImpl deskImpl = new DeskImpl(this);
        Desk desk = new Desk();
        desk.id ="id4";
        desk.name = "name4";
        desk.purpose = "purpose4";
        deskImpl.insert(desk);

        List<Desk> all = deskImpl.findAll();
//        deskImpl.delete("id4");

        all = deskImpl.findAll();
        Desk desk2 = new Desk();
        desk2.id ="id2";
        desk2.name = "name3";
        desk2.purpose = "purpose3";
        deskImpl.update(desk2);

        deskImpl.findAll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
