package com.ning.mvvmplayandroid.ui.adapter

import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.databinding.ItemHotSearchBinding
import com.ning.mvvmplayandroid.databinding.ItemTabBinding
import fule.com.playandroidkotlin.ui.model.HotKeyModel
import fule.com.playandroidkotlin.ui.model.ProjectTitleModel

/**
 *@author: njb
 *@date:   2020/1/26 0026 17:40
 *@desc:   热门搜索适配器
 */
class HotSearcherAdapter(data: List<HotKeyModel>?) :
    BaseQuickAdapter<HotKeyModel, BaseViewHolder>(R.layout.item_hot_search, data) {

    override fun convert(helper: BaseViewHolder, item: HotKeyModel) {
        val itemBinding = DataBindingUtil.bind<ItemHotSearchBinding>(helper.itemView)!!
        itemBinding.hotbean = item
        itemBinding.executePendingBindings()

    }
}