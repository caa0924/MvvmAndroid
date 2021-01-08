package com.ning.mvvmplayandroid.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.ning.mvvmplayandroid.base.BaseViewModel
import com.ning.mvvmplayandroid.ui.adapter.KnowListAdapter
import com.ning.mvvmplayandroid.ui.repository.KnowLedgeDetailRepository
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import fule.com.playandroidkotlin.base.BaseModel
import fule.com.playandroidkotlin.ui.model.ArticleListModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 *@author: njb
 *@date:   2020/1/18 0018 14:08
 *@desc:
 */
class KnowLedgeDetailViewModel(
    private val adapter: KnowListAdapter,
    private val completedListener: CompletedListener
) :BaseViewModel<BaseModel<ArticleListModel>>(){
    private var repository = KnowLedgeDetailRepository()
    private var observer: Observer<BaseModel<ArticleListModel>>? = null


    fun getKnowTab(page: Int, cid: Int) {
        viewModelScope.launch {
            try {
                observer = object : Observer<BaseModel<ArticleListModel>> {
                    override fun onComplete() {
                        completedListener.onCompleted()
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: BaseModel<ArticleListModel>) {
                        if (t.errorCode == 0) {
                            t.data?.let {
                                adapter.run {
                                    setNewData(it.datas)
                                }
                            }
                        }
                    }


                    override fun onError(e: Throwable) {
                        completedListener.onCompleted()
                    }
                }
                repository.getArtTabList(page, cid, observer as Observer<BaseModel<ArticleListModel>>)
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

    override fun loadData() {

    }
}