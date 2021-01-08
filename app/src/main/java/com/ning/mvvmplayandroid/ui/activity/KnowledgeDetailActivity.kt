package com.ning.mvvmplayandroid.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.base.BaseActivity1
import com.ning.mvvmplayandroid.databinding.ActivityKnowledgeDetailBinding
import com.ning.mvvmplayandroid.ui.adapter.ArticleTabAdapter
import com.ning.mvvmplayandroid.ui.adapter.KnowListAdapter
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import com.ning.mvvmplayandroid.ui.view.DividerDecoration
import com.ning.mvvmplayandroid.ui.viewmodel.KnowLedgeDetailViewModel
import com.ning.mvvmplayandroid.ui.viewmodel.KnowDetailListViewModel
import fule.com.playandroidkotlin.ui.model.ChildrenModel
import fule.com.playandroidkotlin.ui.util.AppConstant
import kotlinx.android.synthetic.main.activity_knowledge_detail.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 *@author: njb
 *@date:   2020/1/18 0018 12:36
 *@desc:   知识详情
 */
class KnowledgeDetailActivity : BaseActivity1(), CompletedListener {
    private var cid: Int = 0
    private var titles: String? = null
    private var articleTabAdapter: ArticleTabAdapter? = null
    private var knowledge: MutableList<ChildrenModel>? = null
    private var mBinding: ActivityKnowledgeDetailBinding? = null
    private var viewModel: KnowLedgeDetailViewModel? = null
    private var layoutManager: LinearLayoutManager? = null
    private var viewModelsDetail: KnowDetailListViewModel? = null
    private var knowListAdapter: KnowListAdapter? = null
    private var page:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_knowledge_detail)
        initTabAdapter()
        initDetailListAdapter()
        initIntent()
        initToolBar()
        initListener()
    }

    private fun initTabAdapter() {
        articleTabAdapter = ArticleTabAdapter(null)
        layoutManager = LinearLayoutManager(this)
        layoutManager!!.orientation = LinearLayoutManager.HORIZONTAL
        mBinding!!.rvTitle.layoutManager = layoutManager
        mBinding!!.rvTitle.addItemDecoration(
            DividerDecoration(
                ContextCompat.getColor(
                    this,
                    R.color.cd8d8d8
                ), 1
            )
        )
        mBinding!!.rvTitle.adapter = articleTabAdapter
    }

    private fun initDetailListAdapter() {
        knowListAdapter = KnowListAdapter(null)
        layoutManager = LinearLayoutManager(this)
        mBinding!!.rvContent.layoutManager = layoutManager
        knowListAdapter!!.bindToRecyclerView(rv_content)
        mBinding!!.rvContent.addItemDecoration(
            DividerDecoration(
                ContextCompat.getColor(
                    this,
                    R.color.cd8d8d8
                ), 1
            )
        )
        mBinding!!.rvContent.adapter = knowListAdapter
        viewModelsDetail = KnowDetailListViewModel(knowListAdapter!!, this)
    }

    private fun initIntent() {
        if (intent != null && intent.extras != null) {
            intent.extras.let {
                cid = intent.extras!!.getInt(AppConstant.ARTICLELIST_ID)
                titles = intent.extras!!.getString(AppConstant.ARTICLELIST_TITLE)
                knowledge = intent.extras!!.getParcelableArrayList(AppConstant.CONTENT_DATA_KEY)
            }
            knowledge!![0].isSelect = true
            articleTabAdapter!!.setNewData(knowledge)
            cid = articleTabAdapter!!.data[0].id
            viewModelsDetail!!.getKnowDetailList(0, cid)
        }
    }

    private fun initToolBar() {
        toolbar_title.text = "知识详情"
        toolbar!!.setNavigationOnClickListener { finish() }
    }

    private fun initListener() {
        articleTabAdapter!!.setOnItemClickListener { _, View, position ->
            var list = articleTabAdapter!!.data
            for (m in list!!) {
                m.isSelect = false
            }
            list!![position].isSelect = true
            cid = articleTabAdapter!!.data[position].id
            articleTabAdapter!!.notifyDataSetChanged()
            viewModelsDetail!!.getKnowDetailList(page, cid)
        }

        knowListAdapter!!.setOnItemClickListener { _, View, position ->
            Intent(this, WebViewActivity::class.java).run {
                putExtra(AppConstant.WEBVIEW_TITLE_KEY, knowListAdapter!!.data[position].title)
                putExtra(AppConstant.WEBVIEW_ID_KEY, knowListAdapter!!.data[position].id)
                putExtra(AppConstant.WEBVIEW_URL_KEY, knowListAdapter!!.data[position].link)
                startActivity(this, null)
            }
        }

        mBinding!!.rfKnow.setOnRefreshListener {
            page = 0
            viewModelsDetail!!.getKnowDetailList(page,cid)
            mBinding!!.rfKnow.finishRefresh(200)
        }

        mBinding!!.rfKnow.setOnLoadMoreListener {
            page++
            viewModelsDetail!!.getKnowDetailList(page,cid)
            mBinding!!.rfKnow.finishLoadMore(200)
        }

    }

    override fun onCompleted() {
        closeLoadingDialog()
    }

}