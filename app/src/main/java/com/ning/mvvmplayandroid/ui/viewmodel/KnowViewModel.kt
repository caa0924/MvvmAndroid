package com.ning.mvvmplayandroid.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.ning.mvvmplayandroid.base.BaseViewModel
import com.ning.mvvmplayandroid.ui.adapter.KnowLedgeAdapter
import com.ning.mvvmplayandroid.ui.repository.KnowLedgeRepository
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import fule.com.playandroidkotlin.base.BaseListModel
import fule.com.playandroidkotlin.ui.model.KnowledgeModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch

/**
 *@作者: njb
 *@时间: 2020/1/13 18:17
 *@描述:
 */
class KnowViewModel(
    private val ledgeAdapter: KnowLedgeAdapter,
    private val completedListener: CompletedListener)
    :BaseViewModel<BaseViewModel<KnowViewModel>>() {
    private var repository = KnowLedgeRepository()
    private var observer: Observer<BaseListModel<KnowledgeModel.DataBean>>? = null

    fun getKnow(){
        viewModelScope.launch {
            try{
                observer = object : Observer<BaseListModel<KnowledgeModel.DataBean>>{
                    override fun onComplete() {
                        completedListener.onCompleted()
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: BaseListModel<KnowledgeModel.DataBean>) {
                        if (t.errorCode == 0) {
                            t.data?.let {
                                ledgeAdapter.run { setNewData(it) }
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        completedListener.onCompleted()
                    }
                }
                repository .getKownLedge(observer as Observer<BaseListModel<KnowledgeModel.DataBean>>)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

    }

    override fun loadData() {

    }
}