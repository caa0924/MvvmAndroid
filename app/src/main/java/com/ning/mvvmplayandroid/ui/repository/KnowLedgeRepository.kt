package com.ning.mvvmplayandroid.ui.repository

import com.ning.mvvmplayandroid.base.BaseRepository
import fule.com.playandroidkotlin.base.BaseListModel
import fule.com.playandroidkotlin.ui.model.KnowledgeModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *@作者: njb
 *@时间: 2020/1/13 18:12
 *@描述:
 */
class KnowLedgeRepository : BaseRepository(){
    fun getKownLedge(
        observer: Observer<BaseListModel<KnowledgeModel.DataBean>>
    ) {
        apiServer.tree().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

}