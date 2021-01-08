package com.ning.mvvmplayandroid.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.databinding.ItemHomeBinding
import fule.com.playandroidkotlin.ui.model.ArticleListModel

/**
 *@author: njb
 *@date:   2020/2/11 0011 16:47
 *@desc:   文章列表适配器
 */
class ArtListAdapter (data:List<ArticleListModel.DatasBean>?):
    BaseQuickAdapter<ArticleListModel.DatasBean,BaseViewHolder>(R.layout.item_home,data){

    override fun convert(helper: BaseViewHolder, item: ArticleListModel.DatasBean) {
        val itemBinding = DataBindingUtil.bind<ItemHomeBinding>(helper.itemView)!!
        itemBinding.homebean = item
        itemBinding.executePendingBindings()
    }
}