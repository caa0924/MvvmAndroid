package com.ning.mvvmplayandroid.ui.fragment

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.ui.activity.KnowledgeDetailActivity
import com.ning.mvvmplayandroid.ui.adapter.KnowLedgeAdapter
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import com.ning.mvvmplayandroid.ui.viewmodel.KnowViewModel
import fule.com.playandroidkotlin.ui.util.AppConstant
import kotlinx.android.synthetic.main.fm_know.view.*
import java.util.*

/**
 *@作者: njb
 *@时间: 2020/1/10 15:54
 *@描述: 知识主界面
 */
class KnowledgeFragment: Fragment(),CompletedListener{
    private var mBinding: ViewDataBinding? = null
    private var knowLedgeAdapter: KnowLedgeAdapter? = null
    private var viewModel: KnowViewModel? = null
    private var layoutManager: LinearLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fm_know, container, false)
        val view = mBinding!!.root
        initView(view)
        return mBinding!!.root
    }

    private fun initView(view: View) {
        knowLedgeAdapter = KnowLedgeAdapter(null)
        layoutManager = LinearLayoutManager(activity!!)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL
        view.rv_know.layoutManager = layoutManager
        view.rv_know.adapter = knowLedgeAdapter
        viewModel = KnowViewModel(knowLedgeAdapter!!, this)
        //显示加载动画
        viewModel!!.getKnow()

        knowLedgeAdapter!!.setOnItemClickListener { _, View, position ->
            val options: ActivityOptions = ActivityOptions.makeScaleUpAnimation(View,
                View.width / 2,
                View.height / 2,
                0,
                0)
            Intent(context, KnowledgeDetailActivity::class.java).run {
                putExtra(AppConstant.ARTICLELIST_ID, knowLedgeAdapter!!.data[position].id)
                putExtra(AppConstant.ARTICLELIST_TITLE, knowLedgeAdapter!!.data[position].name)
                putParcelableArrayListExtra(AppConstant.CONTENT_DATA_KEY, knowLedgeAdapter!!.data[position].children as ArrayList<out Parcelable>)
                startActivity(this,options.toBundle())
            }
        }
    }


    override fun onCompleted() {

    }
}