package com.ecar.epark.dbdemo.dao.bean;

import com.ecar.epark.dbdemo.dao.annotation.Table_Column;
import com.ecar.epark.dbdemo.dao.annotation.Table_Name;
import com.ecar.epark.dbdemo.dao.helper.DBHelper;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

@Table_Name(DBHelper.TABLE_BOOK)
public class Book {
    @Table_Column(value = DBHelper.TABLE_BOOK_ID,primarykey = true)
    public String id;
    @Table_Column(DBHelper.TABLE_BOOK_NAME)
    public String name;
    @Table_Column(DBHelper.TABLE_BOOK_AUTHOR)
    public String author;
}
