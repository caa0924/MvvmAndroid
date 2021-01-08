package com.ning.mvvmplayandroid.ui.repository;

import com.ning.mvvmplayandroid.base.BaseRepository;

import fule.com.playandroidkotlin.base.BaseListModel;
import fule.com.playandroidkotlin.ui.model.BannerModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @作者: njb
 * @时间: 2020/1/9 15:12
 * @描述:
 */
public class MainRepository extends BaseRepository {
    public void getList(Observer<BaseListModel<BannerModel>> observer) {
        apiServer.banner().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
