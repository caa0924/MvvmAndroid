package com.ning.mvvmplayandroid.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.ning.mvvmplayandroid.base.BaseViewModel
import com.ning.mvvmplayandroid.ui.adapter.NaviTabAdapter
import com.ning.mvvmplayandroid.ui.repository.NaviRepository
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import fule.com.playandroidkotlin.base.BaseListModel
import fule.com.playandroidkotlin.ui.model.NavigationModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch

/**
 *@author: njb
 *@date:   2020/1/13 0013 21:56
 *@desc:
 */
class NaviViewModel (
    private val tabAdapter: NaviTabAdapter,
    private val completedListener: CompletedListener)
:BaseViewModel<BaseListModel<NavigationModel.DataBean>>(){
    private var repository = NaviRepository()
    private var observer: Observer<BaseListModel<NavigationModel.DataBean>>? = null


    fun getNav(){
        viewModelScope.launch {
            observer = object : Observer<BaseListModel<NavigationModel.DataBean>>{
                override fun onComplete() {
                    completedListener.onCompleted()
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: BaseListModel<NavigationModel.DataBean>) {
                    if (t.errorCode == 0) {
                        t.data?.let {
                            tabAdapter.run { setNewData(it) }
                            tabAdapter.data[0].isSelect = true
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    completedListener.onCompleted()
                }
            }
            repository .getNaviContent(observer as Observer<BaseListModel<NavigationModel.DataBean>>)
        }
    }

    override fun loadData() {

    }
}