package com.dotawang.mvp.model;

import com.dotawang.mvp.base.BaseModel;
import com.dotawang.mvp.bean.BrandBean;
import com.dotawang.mvp.bean.HomeBanner;
import com.dotawang.mvp.bean.HomePost;
import com.dotawang.mvp.bean.HomeSpecial;
import com.dotawang.mvp.bean.M_Base;
import com.dotawang.mvp.http.Http;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Dota.Wang on 2018/11/13.
 */

public class MainModel extends BaseModel{

    public Observable<M_Base<List<HomeBanner>>> getHomeBanner() {
        return Http.getInstance().getSAYDService().homeBanner().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 首页
     * */
    public Observable<M_Base<List<BrandBean>>> homeBrand(){
        return Http.getInstance().getSAYDService().homeBrand();
    }

    /**
     * 热帖
     * */
    public Observable<M_Base<List<HomePost>>> homePost(){
        return Http.getInstance().getSAYDService().homePost();
    }

    public Observable<M_Base<List<HomeSpecial>>> homeSpecial(Map<String,String> map){
        return Http.getInstance().getSAYDService().homeSpecial(map);
    }

}
