package com.happy.base;


import com.happy.base.interfaces.IBindView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class RoadKnife {
    private static Map<Class,IBindView> bindViews = new HashMap<>();
    public static void bindView(Object object) {
        String name = object.getClass().getName();
        try {
            Class<?> aClass = Class.forName(name + "$$BindView");
            IBindView iBindView = bindViews.get(aClass);
            if(iBindView == null){
                iBindView = (IBindView) aClass.newInstance();
            }
            iBindView.bindView(object);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void unBindView(Object object){

    }
}
