package com.ning.mvvmplayandroid.ui.adapter

import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.databinding.ItemKnowledgeDetailTabBinding
import fule.com.playandroidkotlin.ui.model.ChildrenModel

/**
 *@author: njb
 *@date:   2020/1/18 0018 14:03
 *@desc:   文章标题tab
 */
class ArticleTabAdapter(data: List<ChildrenModel>?):
    BaseQuickAdapter<ChildrenModel, BaseViewHolder>(R.layout.item_knowledge_detail_tab, data) {

    override fun convert(helper: BaseViewHolder, item: ChildrenModel) {
        val itemBinding = DataBindingUtil.bind<ItemKnowledgeDetailTabBinding>(helper.itemView)!!
        itemBinding.chidlrenbean = item
        itemBinding.executePendingBindings()

        if (item.isSelect) {
            helper.setTextColor(R.id.tab_name, ContextCompat.getColor(mContext,R.color.colorPrimary))
        } else {
            helper.setTextColor(R.id.tab_name, ContextCompat.getColor(mContext,R.color.c999999))
        }
    }

}