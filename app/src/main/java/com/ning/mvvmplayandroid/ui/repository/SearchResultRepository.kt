package com.ning.mvvmplayandroid.ui.repository

import com.ning.mvvmplayandroid.base.BaseRepository
import fule.com.playandroidkotlin.base.BaseModel
import fule.com.playandroidkotlin.ui.model.SearchModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *@author: njb
 *@date:   2020/2/6 0006 16:14
 *@desc:   搜索列表
 */
class SearchResultRepository : BaseRepository() {
    fun getSearchDetail( page: Int,
                         k:String,
                         observer: Observer<BaseModel<SearchModel>>
    ) {
        apiServer.searchResult(page,k).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}