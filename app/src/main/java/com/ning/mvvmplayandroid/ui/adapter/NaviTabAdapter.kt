package com.ning.mvvmplayandroid.ui.adapter

import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.databinding.ItemNaviTabBinding
import fule.com.playandroidkotlin.ui.model.NavigationModel

/**
 *@author: njb
 *@date:   2020/2/5 0005 18:15
 *@desc:   导航实体类
 */
class NaviTabAdapter(data: List<NavigationModel.DataBean>?) :
    BaseQuickAdapter<NavigationModel.DataBean, BaseViewHolder>(R.layout.item_navi_tab, data) {

    override fun convert(helper: BaseViewHolder, item: NavigationModel.DataBean?) {
        val itemBinding = DataBindingUtil.bind<ItemNaviTabBinding>(helper.itemView)!!
        itemBinding.navibean = item
        itemBinding.executePendingBindings()

        if(item!!.isSelect){
            helper.setTextColor(R.id.tab_name, ContextCompat.getColor(mContext,R.color.colorPrimary))
        }else{
            helper.setTextColor(R.id.tab_name, ContextCompat.getColor(mContext,R.color.c999999))
        }
    }
}