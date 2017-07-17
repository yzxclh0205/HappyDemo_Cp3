package com.ecar.epark.happydemo.dao.base;

import com.ecar.epark.happydemo.dao.bean.Desk;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public interface Dao<M> {

    long insert(M m);

    int delete(String id);

    int update(M m);

    List<M> findAll();
}
