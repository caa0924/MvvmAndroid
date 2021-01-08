package com.ning.mvvmplayandroid.ui.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.OnClick
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.base.BaseActivity1
import com.ning.mvvmplayandroid.databinding.ActivitySearcherBinding
import com.ning.mvvmplayandroid.impl.HistoryImpl
import com.ning.mvvmplayandroid.ui.adapter.HotSearcherAdapter
import com.ning.mvvmplayandroid.ui.adapter.SearchHistoryAdapter
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import com.ning.mvvmplayandroid.ui.viewmodel.HotSearchViewModel
import com.ning.mvvmplayandroid.util.RegexUtils
import com.ning.mvvmplayandroid.util.SoftInputUtils
import com.ning.mvvmplayandroid.util.ToastUtils
import fule.com.playandroidkotlin.ui.util.AppConstant
import kotlinx.android.synthetic.main.toolbar.*

/**
 *@author: njb
 *@date:   2020/1/26 0026 17:07
 *@desc:
 */
class SearchActivity : BaseActivity1(), CompletedListener {
    private var viewModel: HotSearchViewModel? = null
    private var layoutManager: GridLayoutManager? = null
    private var adapter: HotSearcherAdapter? = null
    private var mBinding: ActivitySearcherBinding? = null
    private var historyAdapter: SearchHistoryAdapter? = null
    private var historyList: MutableList<String>? = null
    private var linearLayoutManager:LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_searcher)
        initToolbar()
        initTabAdapter()
        initHistoryAdapter()
        initListener()
    }

    private fun initListener() {

        mBinding!!.etSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (TextUtils.isEmpty(mBinding!!.etSearch.text.toString())) {
                    ToastUtils.ToastShort("请输入关键字")
                    return@OnEditorActionListener false
                }
                if (!RegexUtils.stringFilter(mBinding!!.etSearch.text.toString())) {
                    ToastUtils.ToastShort(this, "您输入的内容不合法")
                    return@OnEditorActionListener false
                }
                // 当按了搜索之后关闭软键盘
                SoftInputUtils.hideSoftInput(this)
                HistoryImpl.addHistory(mBinding!!.etSearch.text.toString())
                Intent(this,SearchResultActivity::class.java).run{
                    putExtra(AppConstant.ARTICLELIST_TITLE, mBinding!!.etSearch.text)
                    startActivity(this, null)
                }
                return@OnEditorActionListener true
            }
            false
        })
        mBinding!!.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }
            override fun afterTextChanged(s: Editable) {
                if (s.length > 0) {
                    mBinding!!.ivDelete.visibility = View.VISIBLE
                } else {
                    mBinding!!.ivDelete.visibility = View.GONE
                }
            }
        })
    }

    private fun initHistoryAdapter() {
        historyAdapter = SearchHistoryAdapter(null)
        linearLayoutManager = LinearLayoutManager(this)
        mBinding!!.rvHistory.layoutManager = linearLayoutManager
        mBinding!!.rvHistory.adapter = historyAdapter
        historyAdapter!!.setOnItemClickListener { adapter, view, position ->
            Intent(this, SearchResultActivity::class.java).run {
                putExtra(AppConstant.ARTICLELIST_TITLE, historyAdapter!!.data[position])
                startActivity(this, null)
            }
        }
    }

    private fun initToolbar() {
        toolbar_title.text = "搜索"
        toolbar!!.setNavigationOnClickListener { finish() }
    }

    private fun initTabAdapter() {
        layoutManager = GridLayoutManager(this, 3)
        mBinding!!.rvSearch.layoutManager = layoutManager
        adapter = HotSearcherAdapter(null)
        mBinding!!.rvSearch.adapter = adapter
        viewModel = HotSearchViewModel(adapter!!, this)
        showLoadingDialog()
        viewModel!!.getHotSearch()

        adapter!!.setOnItemClickListener { _, View, position ->
            val options: ActivityOptions = ActivityOptions.makeScaleUpAnimation(
                View,
                View.width / 2,
                View.height / 2,
                0,
                0
            )
            Intent(this, SearchResultActivity::class.java).run {
                putExtra(AppConstant.ARTICLELIST_TITLE, adapter!!.data[position].name)
                startActivity(this, options.toBundle())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        historyList = HistoryImpl.getHistoryList()
        historyAdapter!!.setNewData(historyList)
    }

    @OnClick(R.id.tv_search_cancel, R.id.iv_delete, R.id.search_history_clear_all_tv)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.tv_search_cancel -> finish()
            R.id.iv_delete -> mBinding!!.etSearch.text.clear()
            R.id.search_history_clear_all_tv -> {
                HistoryImpl.clearHistory()
                if (historyList != null) {
                    historyList!!.clear()
                    historyAdapter!!.notifyDataSetChanged()
                    ToastUtils.ToastShort("清空成功")
                }
            }
        }
    }

    override fun onCompleted() {
        closeLoadingDialog()
    }


}