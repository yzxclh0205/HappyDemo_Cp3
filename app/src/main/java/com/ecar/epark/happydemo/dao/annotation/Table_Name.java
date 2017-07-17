package com.ecar.epark.happydemo.dao.annotation;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注释类
 * 生命周期：运行时
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table_Name {
    String value();//数据库中表名
}
