package com.dotawang.mvp.base;

/**
 * 通过反射机制，以请求数据时具体Model的包名+类型作为ModelType，去找到对应的Model
 * Created by Dota.Wang on 2018/10/31.
 */

public class DataModel {
    public static BaseModel request(String modelType){
        //声明一个空的BaseModel
        BaseModel model = null;

        try {
            //利用反射机制获得对应Model对象的引用
            model = (BaseModel) Class.forName(modelType).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return model;
    }
}
