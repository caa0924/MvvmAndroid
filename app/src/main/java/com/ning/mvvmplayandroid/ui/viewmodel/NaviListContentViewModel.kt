package com.ning.mvvmplayandroid.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.ning.mvvmplayandroid.base.BaseViewModel
import com.ning.mvvmplayandroid.ui.adapter.NaviListContentAdapter
import com.ning.mvvmplayandroid.ui.repository.NaviListContentRepository
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import fule.com.playandroidkotlin.base.BaseListModel
import fule.com.playandroidkotlin.ui.model.NavigationModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch

/**
 *@作者: njb
 *@时间: 2020/1/15 14:34
 *@描述:
 */
class NaviListContentViewModel(
    private var adapter: NaviListContentAdapter,
    private var completedListener: CompletedListener)
    :BaseViewModel<BaseViewModel<NavigationModel.DataBean>>(){
    private var contentRepository = NaviListContentRepository()
    private var observer: Observer<BaseListModel<NavigationModel.DataBean>>? = null

    fun getNaviListContent(){
        viewModelScope.launch {
            try {
                observer = object : Observer<BaseListModel<NavigationModel.DataBean>>{
                    override fun onComplete() {
                        completedListener.onCompleted()
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: BaseListModel<NavigationModel.DataBean>) {
                        if (t.errorCode == 0) {
                            t.data?.let {
                                adapter.run { setNewData(it) }
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        completedListener.onCompleted()
                    }
                }
                contentRepository .getNaviListContent(observer as Observer<BaseListModel<NavigationModel.DataBean>>)
            }catch (e: Exception){
                e.printStackTrace()
            }

        }
    }

    override fun loadData() {

    }

}