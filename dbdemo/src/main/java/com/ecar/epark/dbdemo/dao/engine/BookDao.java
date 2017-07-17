package com.ecar.epark.dbdemo.dao.engine;

import com.ecar.epark.dbdemo.dao.bean.Book;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public interface BookDao {
    long insert(Book book);
    int delete(String id);
    int update(Book book);
    List<Book> findAll();
}
