package com.ning.mvvmplayandroid.ui.repository

import com.ning.mvvmplayandroid.base.BaseRepository
import fule.com.playandroidkotlin.base.BaseListModel
import fule.com.playandroidkotlin.ui.model.BannerModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *@author: njb
 *@date:   2020/2/15 0015 18:49
 *@desc:
 */
class BannerRepository :BaseRepository() {
    fun getBannerList(observer: Observer<BaseListModel<BannerModel>>){
        apiServer.banner()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}