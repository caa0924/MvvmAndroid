package com.ning.mvvmplayandroid.ui.viewmodel

import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.base.BaseViewModel
import com.ning.mvvmplayandroid.ui.adapter.ProjectTabListAdapter
import com.ning.mvvmplayandroid.ui.repository.ProjectListRepository
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import fule.com.playandroidkotlin.base.BaseModel
import fule.com.playandroidkotlin.ui.model.ProjectListModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 *@作者: njb
 *@时间: 2020/1/14 10:16
 *@描述:
 */
class ProjectListViewModel(
    private val adapter: ProjectTabListAdapter,
    private val completedListener: CompletedListener
) :BaseViewModel<BaseModel<ProjectListModel>>(){
    private var repository = ProjectListRepository()
    private var observer: Observer<BaseModel<ProjectListModel>>? = null

    fun getProjectList(page: Int, cid: Int) {
        try{
            observer = object : Observer<BaseModel<ProjectListModel>> {
                override fun onComplete() {
                    completedListener.onCompleted()
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: BaseModel<ProjectListModel>) {
                    if (t.errorCode == 0) {
                        t.data?.let {
                            if (t.data?.datas?.size == 0) {
                                adapter.setEmptyView(R.layout.empty_data)
                            }
                            if (page == 1) {
                                adapter.setNewData(it.datas)
                            } else {
                                it.datas?.let { it1 -> adapter.addData(it1) }
                            }
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    completedListener.onCompleted()
                }
            }
            repository.getProjectList(page, cid, observer)
        }catch (e :Exception){
            e.printStackTrace()
        }

    }

    override fun loadData() {

    }

}