package com.ning.mvvmplayandroid.ui.repository

import com.ning.mvvmplayandroid.base.BaseRepository
import fule.com.playandroidkotlin.base.BaseModel
import fule.com.playandroidkotlin.ui.model.ArticleListModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *@author: njb
 *@date:   2020/2/8 0008 20:35
 *@desc:
 */
class KnowListRepository : BaseRepository() {
    fun getArtList(page: Int,
                   cid:Int,
                   observer: Observer<BaseModel<ArticleListModel>>
    ) {
        apiServer.article(page,cid).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}