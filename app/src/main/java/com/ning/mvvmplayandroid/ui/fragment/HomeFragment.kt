package com.ning.mvvmplayandroid.ui.fragment

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
import com.ning.mvvmplayandroid.ui.adapter.ArtListAdapter
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import com.ning.mvvmplayandroid.ui.view.DividerDecoration
import com.ning.mvvmplayandroid.ui.viewmodel.BannerViewModel
import com.ning.mvvmplayandroid.ui.viewmodel.HomeViewModel
import com.ning.mvvmplayandroid.util.GlideImageLoader
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import fule.com.playandroidkotlin.ui.model.BannerModel
import fule.com.playandroidkotlin.ui.util.AppConstant
import kotlinx.android.synthetic.main.fm_home.view.*

/**
 * @作者: njb
 * @时间: 2020/1/10 18:19
 * @描述: 首页
 */
class HomeFragment : Fragment(), CompletedListener {
    private var mBinding: ViewDataBinding? = null
    private var artListAdapter: ArtListAdapter? = null
    private var viewModel: HomeViewModel? = null
    private var layoutManager: LinearLayoutManager? = null
    private var page: Int = 1
    private var viewHead: View? = null
    private var banner: Banner? = null
    private var bannerModel: List<BannerModel>? = null
    private var viewModels:BannerViewModel ? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fm_home, container, false)
        val view = mBinding!!.root
        viewHead = LayoutInflater.from(context).inflate(R.layout.item_home_banner, null)
        initBanner()
        initView(view)
        initListener(view)

        return mBinding!!.root
    }

    /**
     * 初始化view
     */
    private fun initView(view: View) {

        artListAdapter = ArtListAdapter(null)
        layoutManager = LinearLayoutManager(activity!!)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL
        view.rv_home.layoutManager = layoutManager
        artListAdapter!!.addHeaderView(viewHead)
        view.rv_home.addItemDecoration(DividerDecoration(ContextCompat.getColor(activity!!,R.color.ce7e7e7),2))
        view.rv_home.adapter = artListAdapter
        viewModel = HomeViewModel(artListAdapter!!, this)
        //显示加载动画
        viewModel!!.getArtCircleList(page)

    }

    private fun initBanner() {
        banner = viewHead!!.findViewById(R.id.home_banner)

        //设置banner样式
        banner!!.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        //设置图片加载器
        banner!!.setImageLoader(GlideImageLoader())
        //设置banner动画效果
        banner!!.setBannerAnimation(Transformer.Default)
        //设置自动轮播，默认为true
        banner!!.isAutoPlay(true)
        //设置轮播时间
        banner!!.setDelayTime(3000)
        //设置指示器位置（当banner模式中有指示器时）
        banner!!.setIndicatorGravity(BannerConfig.CENTER)
        //banner设置方法全部调用完毕时最后调用
        banner!!.start()
        viewModels = BannerViewModel(banner!!,this)
        viewModels!!.getBannerList()
    }

    /**
     * 初始化事件
     */
    private fun initListener(view: View) {
        artListAdapter!!.setOnItemClickListener{
            listener,View,position ->
            Intent(context, WebViewActivity::class.java).run {
                putExtra(AppConstant.WEBVIEW_TITLE_KEY, artListAdapter!!.data[position].title)
                putExtra(AppConstant.WEBVIEW_ID_KEY, artListAdapter!!.data[position].id)
                putExtra(AppConstant.WEBVIEW_URL_KEY, artListAdapter!!.data[position].link)
                startActivity(this, null)
            }
        }

        view.rf_home.setOnRefreshListener {
            page = 1
            viewModel!!.getArtCircleList(page)
            view.rf_home.finishRefresh(200)
        }
        view.rf_home.setOnLoadMoreListener {
            page++
            viewModel!!.getArtCircleList(page)
            view.rf_home.finishLoadMore(200)
        }
    }

    override fun onCompleted() {

    }
}
