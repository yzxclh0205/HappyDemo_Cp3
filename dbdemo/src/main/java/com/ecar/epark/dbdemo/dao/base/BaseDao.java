package com.ecar.epark.dbdemo.dao.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ecar.epark.dbdemo.dao.annotation.Table_Column;
import com.ecar.epark.dbdemo.dao.annotation.Table_Name;
import com.ecar.epark.dbdemo.dao.helper.DBHelper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class BaseDao<M> implements Dao<M> {

    private final SQLiteDatabase db;
    private String primaryKey;

    public BaseDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public long insert(M m) {
        ContentValues values = fillColumns(m);
        return db.insert(getTableName(), null, values);
    }


    @Override
    public int delete(String id) {
        return db.delete(getTableName(), getPrimaryKey() + "=?", new String[]{id});
    }

    @Override
    public int update(M m) {
        ContentValues values = fillColumns(m);
        return db.update(getTableName(), values, getPrimaryKey() + "=?", new String[]{getPrimaryValue(m)});
    }


    @Override
    public List<M> findAll() {
        Cursor cursor = db.query(getTableName(), null, null, null, null, null, null);
        List<M> list = null;
        if (cursor != null) {
            list = new ArrayList<>();
            while (cursor.moveToNext()) {
                M m = fillBean(cursor);
                list.add(m);
            }
        }
        cursor.close();
        return list;
    }


    /**
     * 根据实例对象获取表名
     *
     * @return
     */
    public String getTableName() {
        M m = getBean();
        Table_Name annotation = m.getClass().getAnnotation(Table_Name.class);
        if (annotation != null) {
            return annotation.value();
        }
        return m.getClass().getSimpleName();
    }

    public String getPrimaryKey() {
        M m = getBean();
        Field[] declaredFields = m.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Table_Column annotation = field.getAnnotation(Table_Column.class);
            if (annotation != null) {
                boolean primarykey = annotation.primarykey();
                if (primarykey) {
                    return annotation.value();
                }
            }
        }
        return "";
    }

    private String getPrimaryValue(M m) {
        Field[] declaredFields = m.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Table_Column annotation = field.getAnnotation(Table_Column.class);
            if (annotation != null) {
                boolean primarykey = annotation.primarykey();
                if (primarykey) {
                    try {
                        return field.get(m).toString();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "";
    }

    /**
     * 将实例对象属性 对应到列，并对应赋值
     *
     * @param m
     * @return
     */
    private ContentValues fillColumns(M m) {
        ContentValues values = new ContentValues();
        Field[] declaredFields = m.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Table_Column annotation = field.getAnnotation(Table_Column.class);
            if (annotation != null) {
                String columnKey = annotation.value();
                try {
                    String columnValue = field.get(m).toString();
                    values.put(columnKey, columnValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return values;
    }

    private M fillBean(Cursor cursor) {
        M m = getBean();
        Field[] declaredFields = m.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Table_Column annotation = field.getAnnotation(Table_Column.class);
            if (annotation != null) {
                String columnKey = annotation.value();
                int columnIndex = cursor.getColumnIndex(columnKey);
                String columnContent = cursor.getColumnName(columnIndex);
                try {
                    field.set(m, columnContent);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
//        int columnIndex = cursor.getColumnIndex(DBHelper.TABLE_BOOK_NAME);
//        String columnContent = cursor.getColumnName(columnIndex);
//        M bean = getBean();
//        bean.name = columnContent;
        return m;
    }

    /**
     * 根据泛型创建一个实例对象
     *
     * @return
     */
    private M getBean() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if (genericSuperclass != null && genericSuperclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            try {
                return (M) ((Class) actualTypeArguments[0]).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
