package com.ning.mvvmplayandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.ui.adapter.NaviListContentAdapter
import com.ning.mvvmplayandroid.ui.adapter.NaviTabAdapter
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import com.ning.mvvmplayandroid.ui.view.DividerDecoration
import com.ning.mvvmplayandroid.ui.viewmodel.NaviListContentViewModel
import com.ning.mvvmplayandroid.ui.viewmodel.NaviViewModel
import kotlinx.android.synthetic.main.fm_nav.*
import kotlinx.android.synthetic.main.fm_nav.view.*

/**
 *@作者: njb
 *@时间: 2020/1/10 15:54
 *@描述: 导航主界面
 */
class NavigationFragment : Fragment(), CompletedListener {
    private var mBinding: ViewDataBinding? = null
    private var navTabAdapter: NaviTabAdapter? = null
    private var viewModel: NaviViewModel? = null
    private var layoutManager: LinearLayoutManager? = null
    private var navContentAdapter: NaviListContentAdapter? = null
    private var viewModels: NaviListContentViewModel? = null
    private var bScroll: Boolean = false
    private var currentIndex: Int = 0
    private var bClickTab: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fm_nav, container, false)
        val view = mBinding!!.root
        initView(view)
        initListener(view)
        leftRightLink(view)
        return mBinding!!.root
    }

    private fun initView(view: View) {
        navTabAdapter = NaviTabAdapter(null)
        layoutManager = LinearLayoutManager(activity!!)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL
        view.rv_nav.layoutManager = layoutManager
        view.rv_nav.addItemDecoration(DividerDecoration(ContextCompat.getColor(activity!!,R.color.ce7e7e7),2))
        view.rv_nav.adapter = navTabAdapter
        viewModel = NaviViewModel(navTabAdapter!!, this)
        //显示加载动画
        viewModel!!.getNav()

        navContentAdapter = NaviListContentAdapter(null)
        layoutManager = LinearLayoutManager(activity!!)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL
        view.rv_content.layoutManager = layoutManager
        view.rv_content.addItemDecoration(DividerDecoration(ContextCompat.getColor(activity!!,R.color.ce7e7e7),2))
        view.rv_content.adapter = navContentAdapter
        viewModels = NaviListContentViewModel(navContentAdapter!!, this)
        viewModels!!.getNaviListContent()
    }

    private fun initListener(view: View) {
        navTabAdapter!!.setOnItemClickListener { _, View, position ->
            var mList = navTabAdapter!!.data
            for (m in mList!!) {
                m.isSelect = false
            }
            mList!![position].isSelect = true
            bClickTab = true
            selectTab(position,view)
            navTabAdapter!!.notifyDataSetChanged()
        }
    }

    /**
     * Select Left TabLayout to Smooth Right RecyclerView
     */
    private fun selectTab(position: Int, view: View) {
        currentIndex = position
        view.rv_content.stopScroll()
        smoothScrollToPosition(position,view)
    }

    /**
     * Left TabLayout and Right RecyclerView Link
     */
    private fun leftRightLink(view: View) {
        view.rv_content.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                recyclerView.let { super.onScrollStateChanged(it, newState) }
                if (bScroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    scrollRecyclerView(view)
                }
                rightLinkLeft(newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                recyclerView.let { super.onScrolled(it, dx, dy) }
                if (bScroll) {
                    scrollRecyclerView(view)
                }
            }
        })
    }

    /**
     * linearLayoutManager
     */
    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    /**
     * Right RecyclerView link Left TabLayout
     *
     * @param newState RecyclerView Scroll State
     */
    private fun rightLinkLeft(newState: Int) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (bClickTab) {
                bClickTab = false
                return
            }
            val firstPosition: Int = linearLayoutManager.findFirstVisibleItemPosition()
            if (firstPosition != currentIndex) {
                currentIndex = firstPosition
                setChecked(currentIndex)
            }
        }
    }

    private fun smoothScrollToPosition(position: Int, view: View) {
        val firstPosition: Int = linearLayoutManager.findFirstVisibleItemPosition()
        val lastPosition: Int = linearLayoutManager.findLastVisibleItemPosition()
        when {
            position <= firstPosition -> {
                view.rv_content.smoothScrollToPosition(position)
            }
            position <= lastPosition -> {
                val top: Int = view.rv_content.getChildAt(position - firstPosition).top
                view.rv_content.smoothScrollBy(0, top)
            }
            else -> {
                rv_content.smoothScrollToPosition(position)
                bScroll = true
            }
        }
    }

    /**
     * Smooth Right RecyclerView to Select Left TabLayout
     *
     * @param position checked position
     */
    private fun setChecked(position: Int) {
        if (bClickTab) {
            bClickTab = false
        } else {
            navTabAdapter!!.getItem(currentIndex)
        }
        currentIndex = position
    }

    private fun scrollRecyclerView(view: View) {
        bScroll = false
        val indexDistance: Int = currentIndex - linearLayoutManager.findFirstVisibleItemPosition()
        if (indexDistance > 0 && indexDistance < view.rv_content.childCount) {
            val top: Int = view.rv_content.getChildAt(indexDistance).top
            view.rv_content.smoothScrollBy(0, top)
        }
    }

    override fun onCompleted() {
    }

}