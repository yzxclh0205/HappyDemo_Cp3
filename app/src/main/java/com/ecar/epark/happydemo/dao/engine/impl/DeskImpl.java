package com.ecar.epark.happydemo.dao.engine.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ecar.epark.happydemo.dao.DBHelper;
import com.ecar.epark.happydemo.dao.base.BaseDao;
import com.ecar.epark.happydemo.dao.base.Dao;
import com.ecar.epark.happydemo.dao.bean.Desk;
import com.ecar.epark.happydemo.dao.engine.DeskDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class DeskImpl extends BaseDao<Desk> implements DeskDao<Desk> {
    public DeskImpl(Context context) {
        super(context);
    }

//    private final SQLiteDatabase db;

//    public DeskImpl(Context context) {
//        DBHelper helper = new DBHelper(context);
//        db = helper.getWritableDatabase();
//    }
//
//    @Override
//    public long insert(Desk desk) {
//
//        ContentValues values = fillColumns(desk);
//        return db.insert(getTableName(), null, values);
//    }
//
//
//    @Override
//    public long insert(Object o) {
//        return 0;
//    }
//
//    @Override
//    public int delete(String id) {
//        return db.delete(getTableName(), getPrimaryKey().concat("=?"), new String[]{id});
//    }
//
//    @Override
//    public int update(Object o) {
//        return 0;
//    }
//
//    @Override
//    public int update(Desk desk) {
////        ContentValues values = new ContentValues();
////        values.put(DBHelper.TABLE_DESK_NAME,desk.name);
////        values.put(DBHelper.TABLE_DESK_PURPOSE,desk.purpose);
//        ContentValues values = fillColumns(desk);
//        return db.update(getTableName(),values,getPrimaryKey().concat("=?"),new String[]{desk.id});
//    }
//
//    @Override
//    public List<Desk> findAll() {
//        Cursor cursor = db.query(getTableName(), null, null, null, null, null, null);
//        List<Desk> desks = null;
//        if(cursor!=null){
//            desks = new ArrayList<>();
//            while (cursor.moveToNext()){
//                Desk desk = fillBean(cursor);
//                desks.add(desk);
//            }
//        }
//        cursor.close();
//        return desks;
//    }
//
//
//
//    /**
//     * 获取表名
//     * @return
//     */
//    public String getTableName(){
//        return DBHelper.TABLE_DESK;
//    }
//
//    /**
//     * 填充bean值到列
//     */
//    private ContentValues fillColumns(Desk desk) {
//        ContentValues values = new ContentValues();
//        values.put(DBHelper.TABLE_DESK_ID, desk.id);
//        values.put(DBHelper.TABLE_DESK_NAME, desk.name);
//        values.put(DBHelper.TABLE_DESK_PURPOSE, desk.purpose);
//        return values;
//    }
//
//    /**
//     * 获取主键
//     * @return
//     */
//    public String getPrimaryKey(){
//        return DBHelper.TABLE_DESK_ID;
//    }
//
//    /**
//     * 填充列到bean
//     * @param cursor
//     * @return
//     */
//    private Desk fillBean(Cursor cursor) {
//        Desk desk = createBean();
//        int nameIndex = cursor.getColumnIndex(DBHelper.TABLE_DESK_NAME);
//        String nameStr = cursor.getString(nameIndex);
//
//        desk.name = nameStr;
//        return null;
//    }
//
//    /**
//     * 创建具体的实体对象
//     * @return
//     */
//    private Desk createBean() {
//        return new Desk();
//    }
}
