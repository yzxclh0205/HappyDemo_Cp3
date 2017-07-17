package com.ecar.epark.happydemo.dao.bean;

import com.ecar.epark.happydemo.dao.DBHelper;
import com.ecar.epark.happydemo.dao.annotation.Table_Column;
import com.ecar.epark.happydemo.dao.annotation.Table_ID;
import com.ecar.epark.happydemo.dao.annotation.Table_Name;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

@Table_Name(DBHelper.TABLE_BOOK)
public class Book {
    @Table_ID(autoincrement = false)
    @Table_Column("id")
    public String id;
    @Table_Column("name")
    public String name;
    @Table_Column("author")
    public String author;
}
