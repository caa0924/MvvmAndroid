package com.ning.mvvmplayandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.databinding.ItemNumberBinding
import fule.com.playandroidkotlin.ui.model.BannerModel
import java.util.*

/**
 * @作者: njb
 * @时间: 2020/1/9 15:35
 * @描述:
 */
 class MainAdapter(mContext: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val mBeanList = ArrayList<BannerModel>()
    private val inflater: LayoutInflater = LayoutInflater.from(mContext)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemNumberBinding>(
            inflater,
            R.layout.item_number,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = mBeanList[position]
        holder.binding.bean = bean
    }

    fun setData(beanList: List<BannerModel>) {
        mBeanList.addAll(beanList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mBeanList.size
    }

    inner class ViewHolder(val binding: ItemNumberBinding) :
        RecyclerView.ViewHolder(binding.root)
}
