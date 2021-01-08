package com.ning.mvvmplayandroid.ui.repository

import com.ning.mvvmplayandroid.base.BaseRepository
import fule.com.playandroidkotlin.base.BaseListModel
import fule.com.playandroidkotlin.ui.model.ProjectTitleModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *@author: njb
 *@date:   2020/1/13 0013 22:38
 *@desc:
 */
class ProjectRepository : BaseRepository() {
    fun getProjectTree(observer: Observer<BaseListModel<ProjectTitleModel.DataBean>>){
        apiServer.projectTree().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}