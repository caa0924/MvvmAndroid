package com.ning.mvvmplayandroid.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.ning.mvvmplayandroid.base.BaseViewModel
import com.ning.mvvmplayandroid.ui.adapter.KnowListAdapter
import com.ning.mvvmplayandroid.ui.repository.KnowListRepository
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import fule.com.playandroidkotlin.base.BaseModel
import fule.com.playandroidkotlin.ui.model.ArticleListModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch

/**
 *@author: njb
 *@date:   2020/2/8 0008 20:33
 *@desc:   知识详情列表
 */
class KnowDetailListViewModel(
    private val knowListAdapter: KnowListAdapter,
    private val completedListener: CompletedListener
) : BaseViewModel<BaseModel<ArticleListModel>>() {
    private var observer: Observer<BaseModel<ArticleListModel>>? = null
    private var repository = KnowListRepository()

    fun getKnowDetailList(page: Int, cid: Int) {
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
                            t.data?.run {
                                if (page == 0) {
                                    knowListAdapter.run {
                                        setNewData(datas as ArrayList<ArticleListModel.DatasBean>)
                                    }
                                } else {
                                    knowListAdapter.addData(datas!!)
                                }
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        completedListener.onCompleted()
                    }
                }
                repository.getArtList(page, cid, observer as Observer<BaseModel<ArticleListModel>>)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }

    override fun loadData() {

    }
}