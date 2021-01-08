package com.ning.mvvmplayandroid.ui.repository

import com.ning.mvvmplayandroid.base.BaseRepository
import fule.com.playandroidkotlin.base.BaseListModel
import fule.com.playandroidkotlin.ui.model.BannerModel
import fule.com.playandroidkotlin.ui.model.HotKeyModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *@author: njb
 *@date:   2020/1/26 0026 17:37
 *@desc:
 */
class SearcherRepository :BaseRepository(){
    fun getHotSerach(observer: Observer<BaseListModel<HotKeyModel>>) {
        apiServer.hotkey().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}