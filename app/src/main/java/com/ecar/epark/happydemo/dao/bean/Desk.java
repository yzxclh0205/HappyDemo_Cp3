package com.ecar.epark.happydemo.dao.bean;

import com.ecar.epark.happydemo.dao.DBHelper;
import com.ecar.epark.happydemo.dao.annotation.Table_Column;
import com.ecar.epark.happydemo.dao.annotation.Table_Name;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

@Table_Name(DBHelper.TABLE_DESK)
public class Desk {
//    @Table_ID(autoincrement = false)
    @Table_Column(value = DBHelper.TABLE_DESK_ID,primarykey = true)
    public String id;
    @Table_Column(DBHelper.TABLE_DESK_NAME)
    public String name;
    @Table_Column(DBHelper.TABLE_DESK_PURPOSE)
    public String purpose;
}
