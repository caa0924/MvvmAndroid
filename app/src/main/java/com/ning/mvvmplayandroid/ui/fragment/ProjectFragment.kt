package com.ning.mvvmplayandroid.ui.fragment

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.ui.activity.WebViewActivity
import com.ning.mvvmplayandroid.ui.adapter.ProjectTabAdapter
import com.ning.mvvmplayandroid.ui.adapter.ProjectTabListAdapter
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import com.ning.mvvmplayandroid.ui.view.DividerDecoration
import com.ning.mvvmplayandroid.ui.viewmodel.ProjectListViewModel
import com.ning.mvvmplayandroid.ui.viewmodel.ProjectTreeViewModel
import fule.com.playandroidkotlin.ui.model.ProjectTitleModel
import fule.com.playandroidkotlin.ui.util.AppConstant
import kotlinx.android.synthetic.main.fm_project.view.*


/**
 *@作者: njb
 *@时间: 2020/1/10 15:53
 *@描述: 项目主界面
 */
class ProjectFragment : Fragment(), CompletedListener {
    private var mBinding: ViewDataBinding? = null
    private var projectTreeAdapter: ProjectTabAdapter? = null
    private var viewModel: ProjectTreeViewModel? = null
    private var layoutManager: LinearLayoutManager? = null
    private var projectListAdapter: ProjectTabListAdapter? = null
    private var viewModels:ProjectListViewModel? = null
    private var cid:Int = 0
    private var list: List<ProjectTitleModel.DataBean>? = null
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fm_project, container, false)
        val view = mBinding!!.root
        initProjectListAdapter(view)
        initProjectTreeAdapter(view)
        initListener(view)
        return mBinding!!.root
    }

    private fun initListener(view: View) {

        projectListAdapter!!.setOnItemClickListener { _, View, position ->
            val options: ActivityOptions = ActivityOptions.makeScaleUpAnimation(View,
                View.width / 2,
                View.height / 2,
                0,
                0)
            Intent(context, WebViewActivity::class.java).run {
                putExtra(AppConstant.WEBVIEW_TITLE_KEY, projectListAdapter!!.data[position].title)
                putExtra(AppConstant.WEBVIEW_ID_KEY, projectListAdapter!!.data[position].id)
                putExtra(AppConstant.WEBVIEW_URL_KEY, projectListAdapter!!.data[position].link)
                startActivity(this, null)
            }
        }

        projectTreeAdapter!!.setOnItemClickListener { _, View, position ->
            val options: ActivityOptions = ActivityOptions.makeScaleUpAnimation(View,
                View.width / 2,
                View.height / 2,
                0,
                0)
            list = projectTreeAdapter!!.data
            for (m in list!!) {
                m.isSelect = false
            }
            list!![position].isSelect = true
            cid = list!![position].id
            projectTreeAdapter!!.notifyDataSetChanged()
            page = 1
            viewModels!!.getProjectList(page, cid)
        }
        viewModels!!.getProjectList(page, cid)

        view.rf_project_list.setOnLoadMoreListener {
            page++
            viewModels!!.getProjectList(page,cid)
            view.rf_project_list.finishLoadMore(200)
        }
        view.rf_project_list.setOnRefreshListener {
            page = 1
            viewModels!!.getProjectList(page,cid)
            view.rf_project_list.finishRefresh(200)
        }
    }

    private fun initProjectTreeAdapter(view: View) {
        projectTreeAdapter = ProjectTabAdapter(null)
        layoutManager = LinearLayoutManager(activity!!)
        layoutManager!!.orientation = LinearLayoutManager.HORIZONTAL
        view.rv_project.layoutManager = layoutManager
        view.rv_project.addItemDecoration(DividerDecoration(ContextCompat.getColor(activity!!,R.color.cd8d8d8), 1))
        view.rv_project.adapter = projectTreeAdapter
        viewModel = ProjectTreeViewModel(projectTreeAdapter!!, this)
        //显示加载动画
        viewModel!!.getProjectTree()
    }

    private fun initProjectListAdapter(view: View) {
        projectListAdapter = ProjectTabListAdapter(null)
        layoutManager = LinearLayoutManager(activity!!)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL
        projectListAdapter!!.bindToRecyclerView(view.rv_project)
        view.rv_project_list.layoutManager = layoutManager
        view.rv_project_list.addItemDecoration(DividerDecoration(ContextCompat.getColor(activity!!,R.color.cd8d8d8), 1))
        view.rv_project_list.adapter = projectListAdapter
        viewModels = ProjectListViewModel(projectListAdapter!!, this)
    }

    override fun onCompleted() {
    }

}