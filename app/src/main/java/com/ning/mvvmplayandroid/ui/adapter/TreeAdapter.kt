package com.ning.mvvmplayandroid.ui.adapter

import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.databinding.ItemTabBinding

import fule.com.playandroidkotlin.ui.model.ProjectTitleModel

/**
 * @作者: njb
 * @时间: 2020/1/14 18:55
 * @描述:
 */
class TreeAdapter(data: List<ProjectTitleModel.DataBean>?) :
    BaseQuickAdapter<ProjectTitleModel.DataBean, BaseViewHolder>(R.layout.item_tab, data) {

    override fun convert(helper: BaseViewHolder, item: ProjectTitleModel.DataBean) {
        val itemBinding = DataBindingUtil.bind<ItemTabBinding>(helper.itemView)!!
        itemBinding.treebean = item
        itemBinding.executePendingBindings()

        if (item.isSelect) {
            helper.setTextColor(R.id.tab_name,ContextCompat.getColor(mContext,R.color.colorPrimary))
        } else {
            helper.setTextColor(R.id.tab_name,ContextCompat.getColor(mContext,R.color.c999999))
        }
    }
}
