package com.ning.mvvmplayandroid.ui.adapter

import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ning.mvvmplayandroid.R
import fule.com.playandroidkotlin.ui.model.ProjectTitleModel
import com.ning.mvvmplayandroid.databinding.ItemTabBinding

/**
 *@author: njb
 *@date:   2020/2/5  10:51
 *@desc:   项目标题
 */
class ProjectTabAdapter(data:List<ProjectTitleModel.DataBean>?) :
    BaseQuickAdapter<ProjectTitleModel.DataBean, BaseViewHolder>(R.layout.item_tab,data){

    override fun convert(helper: BaseViewHolder, item: ProjectTitleModel.DataBean?) {
        val itemBinding = DataBindingUtil.bind<ItemTabBinding>(helper.itemView)!!
        itemBinding.treebean = item
        itemBinding.executePendingBindings()


        if(item!!.isSelect){
            helper.setTextColor(R.id.tab_name, ContextCompat.getColor(mContext,R.color.colorPrimary))
        }else{
            helper.setTextColor(R.id.tab_name,ContextCompat.getColor(mContext,R.color.c999999))
        }
    }

}