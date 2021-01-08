package com.ning.mvvmplayandroid.ui.repository

import com.ning.mvvmplayandroid.base.BaseRepository
import fule.com.playandroidkotlin.base.BaseListModel
import fule.com.playandroidkotlin.base.BaseModel
import fule.com.playandroidkotlin.ui.model.ArticleListModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *@作者: njb
 *@时间: 2020/1/10 18:00
 *@描述:
 */
class HomeRepository :BaseRepository(){
    fun getArcticList(
        page: Int,
        observer: Observer<BaseModel<ArticleListModel>>) {
        apiServer.articleList(page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}