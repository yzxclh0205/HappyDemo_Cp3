package com.ecar.epark.dbdemo.dao.base;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public interface Dao<M> {
     long insert(M m);
     int delete(String id);
     int update(M m);
     List<M> findAll();
}
