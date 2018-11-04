package com.dotawang.mvp.base;

import android.content.Context;

/**
 * Created by Dota.Wang on 2018/11/4.
 */

public interface IBasePresenter {
    /**
     * 判断网络连接
     * */
    boolean checkNetWork(Context context);

    /**
     * 取消事件总线
     */
    void unsubcrible();
}
