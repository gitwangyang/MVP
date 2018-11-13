package com.dotawang.mvp.base;

import com.dotawang.mvp.http.Http;
import com.dotawang.mvp.utils.NetUtil;

import java.util.Map;


/**
 * Created by Dota.Wang on 2018/10/31.
 */

public abstract class BaseModel {

    public String getCache() {
        return NetUtil.isNetworkAvailable() ? Http.CACHE_CONTROL_AGE : Http.CACHE_CONTROL_CACHE;
    }
}
