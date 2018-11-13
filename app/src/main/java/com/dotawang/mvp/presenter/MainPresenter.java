package com.dotawang.mvp.presenter;


import com.dotawang.mvp.base.BasePresenter;
import com.dotawang.mvp.bean.BrandBean;
import com.dotawang.mvp.bean.HomeBanner;
import com.dotawang.mvp.bean.HomePost;
import com.dotawang.mvp.bean.HomeSpecial;
import com.dotawang.mvp.bean.M_Base;
import com.dotawang.mvp.http.ResultTag;
import com.dotawang.mvp.model.MainModel;
import com.dotawang.mvp.presenter.contract.MainContract;
import com.dotawang.mvp.utils.KLog;

import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Dota.Wang on 2018/10/31.
 */

public class MainPresenter extends BasePresenter<MainContract.IMainView> implements MainContract.BannerPresenter {

    private static final String TAG = "MainPresenter";

    private MainModel mainModel;

    public MainPresenter(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    /**
     * 广告
     * */
    public void getHomeBanner(){
        addSubscription(mainModel.getHomeBanner().subscribe(new Subscriber<M_Base<List<HomeBanner>>>() {
            @Override
            public void onCompleted() {
                //   hideProgress();
                KLog.v("DaLong","completed");
            }

            @Override
            public void onError(Throwable e) {
                mView.showDataError("网络异常", ResultTag.ERROR);
                KLog.v("DaLong","error" +e.getMessage());
            }

            @Override
            public void onNext(M_Base<List<HomeBanner>> listM_base) {
                KLog.v(TAG,"banner"+listM_base.getStatus()+"   "+listM_base.getMssage());
                if(listM_base.getStatus()==1){
                    mView.onBannerSuccess(listM_base.getData());
                }else{
                    mView.showDataError(listM_base.getMssage(),listM_base.getStatus());
                }

            }
        }));


    }
    
    /**
     * 分类
     * */
    public void getBrand() {
        checkNetWork(context);


        Subscription subscription2 = mainModel.homeBrand().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<M_Base<List<BrandBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showDataError("网络异常", ResultTag.ERROR);
                    }

                    @Override
                    public void onNext(M_Base<List<BrandBean>> listM_base) {
                        KLog.v(TAG,"分类"+listM_base.getStatus()+"   "+listM_base.getMssage());
                        if (listM_base.getStatus() == 1) {

                            mView.showDataSuccess(listM_base.getData());
                        } else {
                            mView.showDataError(listM_base.getMssage(), listM_base.getStatus());
                        }
                    }
                });
        addSubscription(subscription2);
    }

    /**
     * 加载社区热帖
     * */
    public void getPost() {

        checkNetWork(context);


        Subscription subscription2 = mainModel.homePost().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<M_Base<List<HomePost>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showDataError("网络异常", ResultTag.ERROR);
                    }

                    @Override
                    public void onNext(M_Base<List<HomePost>> listM_base) {

                        KLog.v(TAG,"社区"+listM_base.getStatus()+"   "+listM_base.getMssage());
                        if (listM_base.getStatus() == 1) {
                            mView.onPostSuccess(listM_base.getData());

                        } else {
                            mView.showDataError(listM_base.getMssage(), listM_base.getStatus());
                        }
                    }
                });
        addSubscription(subscription2);
    }




    /**
     * 加载精选专题
     * */
    public void getSpecial(Map<String, String> map) {


        Subscription subscription1 = mainModel.homeSpecial(map).subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Subscriber<M_Base<List<HomeSpecial>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();
                        mView.showDataError("网络异常", ResultTag.ERROR);
                    }

                    @Override
                    public void onNext(M_Base<List<HomeSpecial>> listM_base) {
                        KLog.v(TAG,"专题"+listM_base.getStatus()+"   "+listM_base.getMssage());
                        mView.hideProgress();
                        if (listM_base.getStatus() == 1) {
                            mView.onSpecialSuccess(listM_base.getData());

                        } else {
                            mView.onSpecialError(listM_base.getMssage(), listM_base.getStatus());
                        }
                    }
                });
        addSubscription(subscription1);
    }
}
