package com.ning.mvvmplayandroid.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.databinding.ItemKnowledgeBinding
import fule.com.playandroidkotlin.ui.model.KnowledgeModel

/**
 *@author: njb
 *@date:   2020/2/1 0001 17:39
 *@desc:   知识适配器·
 */
class KnowLedgeAdapter(data: List<KnowledgeModel.DataBean>?) :
    BaseQuickAdapter<KnowledgeModel.DataBean, BaseViewHolder>(R.layout.item_knowledge, data) {

    override fun convert(helper: BaseViewHolder, item: KnowledgeModel.DataBean) {
        val itemBinding = DataBindingUtil.bind<ItemKnowledgeBinding>(helper.itemView)!!
        itemBinding.knowledge = item
        itemBinding.executePendingBindings()
        item.children.let {
                helper.setText(R.id.tv_knowledge_content, it?.joinToString("    ", transform = { child ->
                    child.name!!
                }))
        }
      /*  if(item.isSelect){
            helper.setBackgroundRes(R.id.tv_knowledge_content, R.drawable.shape_know_selected)
        }else{
            helper.setBackgroundRes(R.id.tv_knowledge_content, R.drawable.shape_know_unselected)
        }*/
    }
}