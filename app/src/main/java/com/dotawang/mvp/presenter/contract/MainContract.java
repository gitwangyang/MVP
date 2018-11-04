package com.dotawang.mvp.presenter.contract;

import com.dotawang.mvp.base.IBaseView;
import com.dotawang.mvp.bean.BrandBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Dota.Wang on 2018/11/4.
 */

public interface MainContract {

    interface IMainView extends IBaseView<List<BrandBean>> {


//        public void onPostSuccess(List<HomePost> homePosts);
//
//        public void onBannerSuccess(List<HomeBanner> homeBanner);
//
//        public void onSpecialSuccess(List<HomeSpecial> homeSpecial);
//
//        public void onSpecialError(String Message,int errorTag);

    }

    interface  BannerPresenter{
        /**
         *  首页Banner界面
         * */
//        public void getHomeBanner();
//        public void getBrand();
//        public void getPost();
//        public void getSpecial(Map<String, String> map);
    }
}
