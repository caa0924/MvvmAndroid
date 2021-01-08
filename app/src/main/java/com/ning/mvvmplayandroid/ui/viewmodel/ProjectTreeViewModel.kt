package com.ning.mvvmplayandroid.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.ning.mvvmplayandroid.base.BaseViewModel
import com.ning.mvvmplayandroid.ui.adapter.ProjectTabAdapter
import com.ning.mvvmplayandroid.ui.repository.ProjectRepository
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import fule.com.playandroidkotlin.base.BaseListModel
import fule.com.playandroidkotlin.ui.model.ProjectTitleModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch

/**
 *@author: njb
 *@date:   2020/1/13 0013 22:41
 *@desc:
 */
class ProjectTreeViewModel(
    private val adapter: ProjectTabAdapter,
    private val completedListener: CompletedListener
): BaseViewModel<BaseListModel<ProjectTitleModel.DataBean>>() {
    private var repository = ProjectRepository()
    private var observer: Observer<BaseListModel<ProjectTitleModel.DataBean>>? = null
    private var cid:Int =0

    fun getProjectTree() {
        viewModelScope.launch {
            try{
                observer = object : Observer<BaseListModel<ProjectTitleModel.DataBean>> {
                    override fun onComplete() {
                        completedListener.onCompleted()
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: BaseListModel<ProjectTitleModel.DataBean>) {
                        if (t.errorCode == 0) {
                            t.data?.let {
                                adapter.run { setNewData(it) }
                                cid = adapter!!.data[0].id
                                adapter!!.data[0].isSelect = true
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        completedListener.onCompleted()
                    }
                }
                repository.getProjectTree(observer as Observer<BaseListModel<ProjectTitleModel.DataBean>>)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    override fun loadData() {

    }
}