package com.ning.mvvmplayandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.databinding.ItemKnowledgeBinding
import fule.com.playandroidkotlin.ui.model.KnowledgeModel
import java.util.*

/**
 * @作者: njb
 * @时间: 2020/1/13 17:57
 * @描述: 知识适配器
 */
class KnowAdapter(mContext: Context) : RecyclerView.Adapter<KnowAdapter.ViewHolder>() {
    private val mBeanList = ArrayList<KnowledgeModel.DataBean>()
    private val inflater: LayoutInflater = LayoutInflater.from(mContext)
    private var mOnKnowListItemClickListener: OnRecyclerViewKnowListItemClickListener? = null

    interface OnRecyclerViewKnowListItemClickListener {
        fun OnKnowListItemClick(itemView: View, position: Int, mBeanList: KnowledgeModel.DataBean)
    }

    fun setOnKnowListItemClickListener(listener: OnRecyclerViewKnowListItemClickListener) {
        this.mOnKnowListItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemKnowledgeBinding>(
            inflater,
            R.layout.item_knowledge,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val knowledge = mBeanList[position]
        holder.binding.knowledge = knowledge
        //此方式只获取到一个值
        //holder.binding.knowList = knowledge.children
        knowledge.children.let {
            holder.binding.tvKnowledgeContent.text = it?.joinToString("    ", transform = { child ->
                child.name!!
            })
        }
        holder.itemView.setOnClickListener {
            mOnKnowListItemClickListener!!.OnKnowListItemClick(holder.itemView, position,mBeanList[position])
        }
    }

    fun setData(beanList: List<KnowledgeModel.DataBean>) {
        mBeanList.addAll(beanList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mBeanList.size
    }

    inner class ViewHolder(val binding: ItemKnowledgeBinding) :
        RecyclerView.ViewHolder(binding.root)

}
