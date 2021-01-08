package com.ning.mvvmplayandroid.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.base.BaseActivity1
import com.ning.mvvmplayandroid.databinding.ActivitySearchDetailBinding
import com.ning.mvvmplayandroid.ui.adapter.SearchDetailAdapter
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import com.ning.mvvmplayandroid.ui.view.DividerDecoration
import com.ning.mvvmplayandroid.ui.viewmodel.SearchResultViewModel
import fule.com.playandroidkotlin.ui.util.AppConstant
import kotlinx.android.synthetic.main.toolbar.*

/**
 *@author: njb
 *@date:   2020/2/6 0006 11:15
 *@desc:
 */
class SearchResultActivity : BaseActivity1(), CompletedListener {
    private var mBinding: ActivitySearchDetailBinding? = null
    private var viewModel: SearchResultViewModel? = null
    private var searchDetailAdapter: SearchDetailAdapter? = null
    private var layoutManager: LinearLayoutManager? = null
    private var key: String? = null
    private var page: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_detail)
        initToolBar()
        initIntent()
        initAdapter()
        initListener()
    }

    private fun initToolBar() {
        toolbar_title.text = "搜索结果"
        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun initIntent() {
        if (intent != null && intent.extras != null) {
            intent.extras.let {
                key = intent.extras!!.getString(AppConstant.ARTICLELIST_TITLE)
            }
        }
    }

    private fun initAdapter() {
        searchDetailAdapter = SearchDetailAdapter(null)
        layoutManager = LinearLayoutManager(this)
        mBinding!!.rvDetail.layoutManager = layoutManager
        mBinding!!.rvDetail.addItemDecoration(
            DividerDecoration(
                ContextCompat.getColor(
                    this,
                    R.color.cd8d8d8
                ), 1
            )
        )
        searchDetailAdapter!!.bindToRecyclerView(mBinding!!.rvDetail)
        mBinding!!.rvDetail.adapter = searchDetailAdapter
        viewModel = SearchResultViewModel(searchDetailAdapter!!, this)
        showLoadingDialog()
        viewModel!!.getSearchDetailList(page, key ?: "");
    }

    private fun initListener() {
        searchDetailAdapter!!.setOnItemClickListener { listener, View, position ->
            Intent(this, WebViewActivity::class.java).run {
                putExtra(AppConstant.WEBVIEW_TITLE_KEY, searchDetailAdapter!!.data[position].title)
                putExtra(AppConstant.WEBVIEW_ID_KEY, searchDetailAdapter!!.data[position].id)
                putExtra(AppConstant.WEBVIEW_URL_KEY, searchDetailAdapter!!.data[position].link)
                startActivity(this, null)
            }
        }
        mBinding!!.rfDetail.setOnLoadMoreListener {
            page++
            viewModel!!.getSearchDetailList(page, key ?: "")
            showLoadingDialog()
            mBinding!!.rfDetail.finishLoadMore(200)
        }

        mBinding!!.rfDetail.setOnRefreshListener {
            page = 0
            showLoadingDialog()
            viewModel!!.getSearchDetailList(page, key ?: "")
            mBinding!!.rfDetail.finishRefresh(200)
        }
    }

    override fun onCompleted() {
        closeLoadingDialog()
    }
}