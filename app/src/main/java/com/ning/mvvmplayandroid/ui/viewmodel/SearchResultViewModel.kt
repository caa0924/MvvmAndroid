package com.ning.mvvmplayandroid.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.base.BaseViewModel
import com.ning.mvvmplayandroid.ui.adapter.SearchDetailAdapter
import com.ning.mvvmplayandroid.ui.repository.SearchResultRepository
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import fule.com.playandroidkotlin.base.BaseModel
import fule.com.playandroidkotlin.ui.model.SearchModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch

/**
 *@author: njb
 *@date:   2020/2/6 0006 11:52
 *@desc:
 */
class SearchResultViewModel(
    private val adapter: SearchDetailAdapter,
    private val completedListener: CompletedListener
) : BaseViewModel<BaseModel<SearchModel>>() {

    private var observer: Observer<BaseModel<SearchModel>>? = null
    private var repository = SearchResultRepository()

    fun getSearchDetailList(page: Int, k: String) {
        viewModelScope.launch {
            try {
                observer = object : Observer<BaseModel<SearchModel>> {
                    override fun onComplete() {
                        completedListener.onCompleted()
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: BaseModel<SearchModel>) {
                        if (t.errorCode == 0) {
                            t.data?.let {
                                if (page == 0) {
                                    if (t.data!!.datas!!.size == 0) {
                                        adapter.setEmptyView(R.layout.empty_data)
                                    }
                                    adapter.run { setNewData(it.datas as ArrayList<SearchModel.DataBean>) }
                                } else {
                                    adapter.addData(it.datas!!)
                                }
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        completedListener.onCompleted()
                    }

                }
                repository.getSearchDetail(
                    page, k,
                    observer as Observer<BaseModel<SearchModel>>
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }

    override fun loadData() {

    }
}