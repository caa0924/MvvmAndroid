package com.ning.mvvmplayandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.databinding.ItemProjectListBinding
import fule.com.playandroidkotlin.ui.model.ProjectListModel
import java.util.*

/**
 *@作者: njb
 *@时间: 2020/1/14 9:54
 *@描述: 项目列表适配器
 */
class ProjectListAdapter(mContext: Context) :
    RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {
    private val mBeanList = ArrayList<ProjectListModel.DatasBean>()
    private val inflater: LayoutInflater = LayoutInflater.from(mContext)
    private var mOnListItemClickListener: OnRecyclerViewListItemClickListener? = null

    interface OnRecyclerViewListItemClickListener {
        fun OnListItemClick(itemView: View, position: Int,mBeanList: ProjectListModel.DatasBean)
    }

    fun setOnListItemClickListener(listener: OnRecyclerViewListItemClickListener) {
        this.mOnListItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemProjectListBinding>(
            inflater,
            R.layout.item_project_list,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return mBeanList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val projectListBean = mBeanList[position]
        holder.binding.projectlistBean = projectListBean
        holder.itemView.setOnClickListener {
            mOnListItemClickListener!!.OnListItemClick(holder.itemView, position,mBeanList[position])
        }
    }

    fun setData(beanList: List<ProjectListModel.DatasBean>?){
        beanList?.let { mBeanList.addAll(it) }
        notifyDataSetChanged()
    }

    fun removeDataList(){
        this.mBeanList.removeAll(mBeanList);
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemProjectListBinding) :
        RecyclerView.ViewHolder(binding.root)
}