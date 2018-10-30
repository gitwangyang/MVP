package com.dotawang.mvp.view;

import com.dotawang.mvp.base.IBaseView;

/**
 *
 * Created by Dota.Wang on 2018/10/31.
 */
public interface MainView extends IBaseView {
    /**
     * 当数据请求成功后，调用此接口显示数据
     * @param data
     */
   void showData(String data);

    /**
     * 当数据请求失败后，调用此接口显示提示的失败信息
     * @param msg
     */
   void showFailureMessage(String msg);

    /**
     * 当数据请求异常时，调用此接口
     */
   void showErrorMessage();
}
