package com.ning.mvvmplayandroid.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.databinding.ItemProjectListBinding
import fule.com.playandroidkotlin.ui.model.ProjectListModel

/**
 *@author: njb
 *@date:   2020/2/5 0005 11:54
 *@desc:   项目列表
 */
class ProjectTabListAdapter(data: List<ProjectListModel.DatasBean>?):
  BaseQuickAdapter<ProjectListModel.DatasBean,BaseViewHolder>(R.layout.item_project_list,data){

    override fun convert(helper: BaseViewHolder, item: ProjectListModel.DatasBean?) {
        val itemBinding = DataBindingUtil.bind<ItemProjectListBinding>(helper.itemView)!!
        itemBinding.projectlistBean = item
        itemBinding.executePendingBindings()
    }
}