package com.ning.mvvmplayandroid.ui.repository

import com.ning.mvvmplayandroid.base.BaseRepository
import fule.com.playandroidkotlin.base.BaseModel
import fule.com.playandroidkotlin.ui.model.ProjectListModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *@作者: njb
 *@时间: 2020/1/14 10:11
 *@描述:
 */
class ProjectListRepository : BaseRepository(){
    fun getProjectList(
        page:Int,
        cid:Int,
        observer: Observer<BaseModel<ProjectListModel>>?
    ){
        observer?.let {
            apiServer.projectList(page,cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it)
        }
    }
}