package com.ning.mvvmplayandroid.ui.viewmodel

import com.ning.mvvmplayandroid.ui.repository.MainRepository
import com.ning.mvvmplayandroid.ui.adapter.MainAdapter
import com.ning.mvvmplayandroid.ui.view.CompletedListener

import fule.com.playandroidkotlin.base.BaseListModel
import fule.com.playandroidkotlin.ui.model.BannerModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @作者: njb
 * @时间: 2020/1/9 15:34
 * @描述:
 */
class MainViewModel(
    private val adapter: MainAdapter,
    private val completedListener: CompletedListener) {

    private var repository = MainRepository()
    private var observer: Observer<BaseListModel<BannerModel>>? = null

    fun getList() {
        observer = object : Observer<BaseListModel<BannerModel>> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(listBaseBean: BaseListModel<BannerModel>) {
                if (listBaseBean.errorCode == 0) {
                    listBaseBean.data?.let { adapter.setData(it) }
                }
            }

            override fun onError(e: Throwable) {
                completedListener.onCompleted()
            }

            override fun onComplete() {
                completedListener.onCompleted()
            }
        }
        repository.getList(observer)
    }
}
