package com.dotawang.mvp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.dotawang.mvp.utils.AppManager;
import com.dotawang.mvp.utils.KLog;
import com.dotawang.mvp.utils.Utils;
import com.dotawang.mvp.view.MainActivity;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 *  Created by Dota.Wang on 2018/10/30.
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG             #
 * #                                                   #
 */

public class AppApplication extends Application {
    private static AppApplication instance;
    private List<Activity> activityList = new LinkedList<Activity>();
    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化工具类
        Utils.init(this);
        //注册监听每个activity的生命周期,便于堆栈式管理
        registerActivityLifecycleCallbacks(mCallbacks);
        //开启打印日志
        KLog.init(true);
        //初始化全局异常崩溃
//        initCrash();
        initLeakCanary();
        initOkhttp();
    }

    /**
     * 获得当前app运行的AppContext
     */
    public static AppApplication getInstance(){
        if (null == instance){
            instance = new AppApplication();
        }
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private ActivityLifecycleCallbacks mCallbacks = new ActivityLifecycleCallbacks() {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            AppManager.getAppManager().addActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            AppManager.getAppManager().removeActivity(activity);
        }
    };

    /**
     * 内存泄漏
     * */
    private RefWatcher refWatcher;
    public static RefWatcher getRefWatcher(Context context){
        AppApplication application = (AppApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private void initLeakCanary() {
        if (BuildConfig.DEBUG) {
            refWatcher = LeakCanary.install(this);
        } else {
            refWatcher = installLeakCanary();
        }
    }
    /**
     * release版本使用此方法
     */
    protected RefWatcher installLeakCanary() {
        return RefWatcher.DISABLED;
    }

    private void initOkhttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    // 遍历所有Activity并finish
    public void exitNoMain() {
        for (Activity activity : activityList) {
            if (activity instanceof MainActivity) {

            }else {
                activity.finish();
            }
        }
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (null!= activity){
            activityList.add(activity);
            KLog.v(TAG,"添加数据了");
        }
    }

    // 添加Activity到容器中
    public void clearActivity() {
        activityList.clear();
    }
}
