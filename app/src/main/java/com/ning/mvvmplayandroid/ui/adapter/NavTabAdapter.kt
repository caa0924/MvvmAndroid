package com.ning.mvvmplayandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.databinding.ItemNaviTabBinding
import fule.com.playandroidkotlin.ui.model.NavigationModel
import java.util.*

/**
 *@author: njb
 *@date:   2020/1/13 0013 21:37
 *@desc:   导航标题适配器
 */
class NavTabAdapter(mContext:Context) :RecyclerView.Adapter<NavTabAdapter.ViewHolder>() {
    private val mBeanList = ArrayList<NavigationModel.DataBean>()
    private val inflater: LayoutInflater = LayoutInflater.from(mContext)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemNaviTabBinding>(
            inflater,
            R.layout.item_navi_tab,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val navibean = mBeanList[position]
        holder.binding.navibean = navibean
    }

    fun setData(beanList: List<NavigationModel.DataBean>) {
        mBeanList.addAll(beanList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mBeanList.size
    }

    inner class ViewHolder(val binding: ItemNaviTabBinding) :
        RecyclerView.ViewHolder(binding.root)
}