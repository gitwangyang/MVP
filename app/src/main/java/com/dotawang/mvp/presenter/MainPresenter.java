package com.dotawang.mvp.presenter;

import com.dotawang.mvp.base.BasePresenter;
import com.dotawang.mvp.base.DataModel;
import com.dotawang.mvp.constant.ModelType;
import com.dotawang.mvp.net.IRequestCallback;
import com.dotawang.mvp.iview.IMainView;

/**
 * Created by Dota.Wang on 2018/10/31.
 */

public class MainPresenter extends BasePresenter<IMainView> {

    /**
     * 获取网络数据
     */
    public void getData(String params){
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }

        //显示正在加载进度条
        getView().showLoading();

        //调用Model请求数据
        DataModel
                // 设置请求标识token
                .request(ModelType.API_MAIN_DATA)
                //添加请求参数，没有则不添加
                .params("")
                //注册监听回调
                .execute(new IRequestCallback<String>() {
                    @Override
                    public void success(String data) {
                        //调用view接口显示数据
                        getView().showData(data);
                    }

                    @Override
                    public void fail(String msg) {
                        //调用view接口提示失败信息
                        getView().showFailureMessage(msg);
                    }

                    @Override
                    public void onError() {
                        getView().showErrorMessage();
                    }

                    @Override
                    public void onComplete() {
                        //隐藏正在加载进度条
                        getView().hideLoading();
                    }
                });
    }
}
