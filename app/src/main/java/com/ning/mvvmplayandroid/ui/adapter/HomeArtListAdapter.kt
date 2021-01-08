package com.ning.mvvmplayandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.databinding.ItemHomeBinding
import fule.com.playandroidkotlin.ui.model.ArticleListModel

/**
 *@作者: njb
 *@时间: 2020/1/10 17:42
 *@描述: 首页列表适配器
 */
class HomeArtListAdapter(mContext: Context) :
    RecyclerView.Adapter<HomeArtListAdapter.ViewHolder>() {
    private val mArtList = ArrayList<ArticleListModel.DatasBean>()
    private val inflater: LayoutInflater = LayoutInflater.from(mContext)
    private var mOnArtListItemClickListener: OnRecyclerViewArtListItemClickListener? = null

    interface OnRecyclerViewArtListItemClickListener {
        fun OnArtListItemClick(itemView: View, position: Int, mBeanList: ArticleListModel.DatasBean)
    }

    fun setOnArtListItemClickListener(listener: OnRecyclerViewArtListItemClickListener) {
        this.mOnArtListItemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemHomeBinding>(
            inflater,
            R.layout.item_home,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var homebean = mArtList[position]
        holder.binding.homebean = homebean
        holder.itemView.setOnClickListener {
            mOnArtListItemClickListener!!.OnArtListItemClick(holder.itemView, position,mArtList[position])
        }
    }

    fun setData(beanList: ArrayList<ArticleListModel.DatasBean>) {
        mArtList.addAll(beanList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mArtList.size
    }

    inner class ViewHolder(val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

}
