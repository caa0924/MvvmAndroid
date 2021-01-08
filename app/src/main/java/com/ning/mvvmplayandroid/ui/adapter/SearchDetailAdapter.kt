package com.ning.mvvmplayandroid.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.databinding.ItemHomeBinding
import com.ning.mvvmplayandroid.databinding.ItemSearchDetailBinding
import fule.com.playandroidkotlin.ui.model.ArticleListModel
import fule.com.playandroidkotlin.ui.model.SearchModel

/**
 *@author: njb
 *@date:   2020/2/6 0006 11:54
 *@desc:  搜索结果
 */
class SearchDetailAdapter(data:List<SearchModel.DataBean>?):
BaseQuickAdapter<SearchModel.DataBean,BaseViewHolder>(R.layout.item_search_detail,data){
    override fun convert(helper: BaseViewHolder, item: SearchModel.DataBean) {
        var itemBinding = DataBindingUtil.bind<ItemSearchDetailBinding>(helper.itemView)!!
        itemBinding.searchdetail = item
        itemBinding.executePendingBindings()
        if(item.chapterName!!.isNotEmpty()){
            helper.setText(R.id.tv_type, item.chapterName)
            helper.getView<TextView>(R.id.tv_type).visibility = View.VISIBLE
        }else{
            helper.getView<TextView>(R.id.tv_type).visibility = View.INVISIBLE
        }

    }
}