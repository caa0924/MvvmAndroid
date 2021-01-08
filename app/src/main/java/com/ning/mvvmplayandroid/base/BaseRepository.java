package com.ning.mvvmplayandroid.base;


import com.ning.mvvmplayandroid.api.ApiRetrofit;

import com.ning.mvvmplayandroid.api.ApiServer;

public abstract class BaseRepository {
    public ApiServer apiServer;

    public BaseRepository() {
        if (apiServer == null) {
            apiServer = ApiRetrofit.getInstance().getApiService();
        }
    }

}
