package com.ecar.epark.happydemo.dao.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.ecar.epark.happydemo.dao.DBHelper;
import com.ecar.epark.happydemo.dao.annotation.Table_Column;
import com.ecar.epark.happydemo.dao.annotation.Table_ID;
import com.ecar.epark.happydemo.dao.annotation.Table_Name;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public abstract class BaseDao<M> implements Dao<M> {
    private final SQLiteDatabase db;

    public BaseDao(Context context) {
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    @Override
    public long insert(M m) {
        ContentValues values = fillColumns(m);
//        values.clear();
//        values.put(DBHelper.TABLE_DESK_ID,"id3");
//        values.put(DBHelper.TABLE_DESK_NAME,"name3");
//        values.put(DBHelper.TABLE_DESK_PURPOSE,"purpose3");
        return db.insert(getTableName(), null, values);
    }


    @Override
    public int delete(String id) {
        return db.delete(getTableName(), getPrimaryKey().concat("=?"), new String[]{id});
    }


    @Override
    public int update(M m) {
//        ContentValues values = new ContentValues();
//        values.put(DBHelper.TABLE_DESK_NAME,desk.name);
//        values.put(DBHelper.TABLE_DESK_PURPOSE,desk.purpose);
        ContentValues values = fillColumns(m);
        return db.update(getTableName(), values, getPrimaryKey().concat("=?"), new String[]{getPrimaryValue(m)});
    }

    @Override
    public List<M> findAll() {
        Cursor cursor = db.query(getTableName(), null, null, null, null, null, null);
        List<M> desks = null;
        if (cursor != null) {
            desks = new ArrayList<>();
            while (cursor.moveToNext()) {
                M m = fillBean(cursor);
                desks.add(m);
            }
        }
        cursor.close();
        return desks;
    }


    /**
     * 获取表名
     *
     * @return
     */
    public String getTableName() {
        M m = getBean();
        Table_Name annotation = m.getClass().getAnnotation(Table_Name.class);
        String value = annotation.value();
        if (!TextUtils.isEmpty(value)) {
            return value;
        }
        return m.getClass().getName();
    }

    /**
     * 填充bean值到列
     * 1.获取到bean 的属性对应的列名（注解value） ，2. 将属性的值作为列value
     */
    private ContentValues fillColumns(M m) {
        ContentValues values = new ContentValues();
        Field[] fields = m.getClass().getDeclaredFields();
        for (Field field : fields) {
            //权限
            field.setAccessible(true);
            Table_Column annotation = field.getAnnotation(Table_Column.class);
            String columnKey = annotation.value();
            try {
                //万一m这个对象里头没有field这个属性呢
                String columnValue = field.get(m).toString();
                values.put(columnKey, columnValue);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
//        values.put(DBHelper.TABLE_DESK_NAME, desk.name);
//        values.put(DBHelper.TABLE_DESK_PURPOSE, desk.purpose);
        return values;
    }

    /**
     * 获取主键
     *
     * @return
     */
    public String getPrimaryValue(M m) {
//        M m = getBean();
        Field[] declaredFields = m.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Table_ID annotation = field.getAnnotation(Table_ID.class);
            if (annotation != null) {
//                boolean autoincrement = annotation.autoincrement();
                try {
                    String id = field.get(m).toString();
                    return id;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public String getPrimaryKey() {
        M m = getBean();
        Field[] declaredFields = m.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Table_Column annotation = field.getAnnotation(Table_Column.class);
            if (annotation != null) {
//                boolean autoincrement = annotation.autoincrement();
//                return field.getName();
                boolean primarykey = annotation.primarykey();
                if(primarykey){
                    return annotation.value();
                }
            }
        }
        return "";
    }

//    public String getPrimaryKey() {
//        M m = getBean();
//        Field[] declaredFields = m.getClass().getDeclaredFields();
//        for (Field field : declaredFields) {
//            field.setAccessible(true);
//            Table_ID annotation = field.getAnnotation(Table_ID.class);
//            if (annotation != null) {
////                boolean autoincrement = annotation.autoincrement();
////                return field.getName();
//                Table_Column annotation1 = field.getAnnotation(Table_Column.class);
//                return annotation1.value();
//            }
//        }
//        return "";
//    }

    /**
     * 填充列到bean
     *
     * @param cursor
     * @return
     */
    private M fillBean(Cursor cursor) {
        M m = getBean();
        Field[] declaredFields = m.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Table_Column annotation = field.getAnnotation(Table_Column.class);
            if (annotation != null) {
                String columnkey = annotation.value();
                int valueIndex = cursor.getColumnIndex(columnkey);
                String value = cursor.getString(valueIndex);
                try {
                    field.set(m,value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return m;
    }

    /**
     * 创建具体的实体对象
     *
     * @return
     */
    private M getBean() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if(genericSuperclass!=null && genericSuperclass instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            try {
                M m = (M) ((Class) actualTypeArguments[0]).newInstance();
                return m;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
