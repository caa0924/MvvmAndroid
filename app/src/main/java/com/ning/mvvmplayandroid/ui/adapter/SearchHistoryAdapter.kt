package com.ning.mvvmplayandroid.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ning.mvvmplayandroid.R

/**
 *@author: njb
 *@date:   2020/2/18 0018 18:59
 *@desc:
 */
class SearchHistoryAdapter(data: MutableList<String>?) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_search_history, data) {
    override fun convert(helper: BaseViewHolder, item: String) {
        with(helper) {
            setText(R.id.tv_search_key, item).addOnClickListener(R.id.iv_clear)
        }
    }
}