package com.ning.mvvmplayandroid.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.databinding.ItemHomeBinding
import fule.com.playandroidkotlin.ui.model.ArticleListModel

/**
 *@author: njb
 *@date:   2020/2/9 0009 16:36
 *@desc:   知识列表适配器
 */
class KnowListAdapter(data: MutableList<ArticleListModel.DatasBean>?) :
    BaseQuickAdapter<ArticleListModel.DatasBean, BaseViewHolder>(
        R.layout.item_home,
        data
    ) {
    override fun convert(helper: BaseViewHolder, item: ArticleListModel.DatasBean) {
        val itemBinding = DataBindingUtil.bind<ItemHomeBinding>(helper.itemView)!!
        itemBinding.homebean = item
        itemBinding.executePendingBindings()
        with(helper){
            if (item.chapterName!!.isNotEmpty()) {
                setText(R.id.tv_type, item.chapterName)
                getView<TextView>(R.id.tv_type).visibility = View.VISIBLE
            } else {
                getView<TextView>(R.id.tv_type).visibility = View.INVISIBLE
            }
        }
    }
}