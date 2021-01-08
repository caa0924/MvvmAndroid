package com.ning.mvvmplayandroid.ui.viewmodel

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ning.mvvmplayandroid.base.BaseViewModel
import com.ning.mvvmplayandroid.ui.adapter.ArtListAdapter
import com.ning.mvvmplayandroid.ui.repository.HomeRepository
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import fule.com.playandroidkotlin.base.BaseModel
import fule.com.playandroidkotlin.ui.model.ArticleListModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 *@作者: njb
 *@时间: 2020/1/10 17:55
 *@描述:
 */
class HomeViewModel (
    private val adapter: ArtListAdapter,
    private val completedListener: CompletedListener
):BaseViewModel<BaseModel<ArticleListModel>>() {
    private var observer: Observer<BaseModel<ArticleListModel>>? = null

    private var repository = HomeRepository()

    fun getArtCircleList(page:Int) {
        //viewModelScope是一个绑定到当前viewModel的作用域  当ViewModel被清除时会自动取消该作用域，所以不用担心内存泄漏为问题
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
                                if (page == 1) {
                                    adapter.setNewData(it.datas)
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
                repository.getArcticList(
                    page,
                    observer as Observer<BaseModel<ArticleListModel>>
                )

            } catch (e: Exception) {
                e.stackTrace
            }
        }

    }

    override fun loadData() {

    }
}