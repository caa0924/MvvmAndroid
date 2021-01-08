package com.ning.mvvmplayandroid.ui.repository

import com.ning.mvvmplayandroid.base.BaseRepository
import fule.com.playandroidkotlin.base.BaseListModel
import fule.com.playandroidkotlin.ui.model.NavigationModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *@作者: njb
 *@时间: 2020/1/15 14:37
 *@描述:
 */
class NaviListContentRepository : BaseRepository() {
    fun getNaviListContent(observer: Observer<BaseListModel<NavigationModel.DataBean>>){
        apiServer.navicontent().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)

    }
}