package com.dotawang.mvp.base;

import android.content.Context;

import com.dotawang.mvp.utils.NetUtil;
import com.dotawang.mvp.utils.ToastUtils;


import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Dota.Wang on 2018/10/30.
 */

public class BasePresenter<V extends IBaseView> implements IBasePresenter{


    public V mView;
    public Context context;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public boolean checkNetWork(Context context) {
        if(!NetUtil.isNetworkAvailable()) {
            ToastUtils.showShort("网络不给力");
        }
        return NetUtil.isNetworkAvailable();
    }


    public void attachView(V view,Context context) {
        this.mView = view;
        this.context=context;
    }


    /**
     * 事件订阅
     * */
    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }


    @Override
    public void unsubcrible() {
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
        mView=null;
        context=null;
        this.mCompositeSubscription=null;
    }
}
