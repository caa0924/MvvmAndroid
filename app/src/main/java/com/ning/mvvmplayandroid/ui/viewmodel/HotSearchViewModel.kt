package com.ning.mvvmplayandroid.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.ning.mvvmplayandroid.base.BaseViewModel
import com.ning.mvvmplayandroid.ui.adapter.HotSearcherAdapter
import com.ning.mvvmplayandroid.ui.repository.HomeRepository
import com.ning.mvvmplayandroid.ui.repository.SearcherRepository
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import fule.com.playandroidkotlin.base.BaseListModel
import fule.com.playandroidkotlin.ui.model.HotKeyModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 *@author: njb
 *@date:   2020/1/26 0026 17:39
 *@desc:
 */
class HotSearchViewModel(private val adapter: HotSearcherAdapter,
                         private val completedListener: CompletedListener
) :BaseViewModel<BaseListModel<HotKeyModel>>(){
    private var observer: Observer<BaseListModel<HotKeyModel>>? = null
    private var repository = SearcherRepository()

    fun getHotSearch() {
        viewModelScope.launch {
            try {
                observer = object : Observer<BaseListModel<HotKeyModel>> {
                    override fun onComplete() {
                        completedListener.onCompleted()
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: BaseListModel<HotKeyModel>) {
                        if (t.errorCode == 0) {
                            t.data?.let {
                                adapter.run {
                                    setNewData(it)
                                }
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        completedListener.onCompleted()
                    }

                }
                repository.getHotSerach(observer as Observer<BaseListModel<HotKeyModel>>
                )
            }catch (e : Exception){
                e.printStackTrace()
            }
        }

    }

    override fun loadData() {

    }
}