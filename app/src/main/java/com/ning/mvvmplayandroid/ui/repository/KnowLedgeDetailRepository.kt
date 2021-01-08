package com.ning.mvvmplayandroid.ui.repository

import com.ning.mvvmplayandroid.base.BaseRepository
import fule.com.playandroidkotlin.base.BaseModel
import fule.com.playandroidkotlin.ui.model.ArticleListModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *@author: njb
 *@date:   2020/1/18 0018 14:11
 *@desc:  知识详情列表
 */
class KnowLedgeDetailRepository : BaseRepository() {
    fun getArtTabList(
        page:Int,
        cid:Int,
        observer: Observer<BaseModel<ArticleListModel>>
    ){
        observer?.let {
            apiServer.article(page,cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it)
        }
    }
}