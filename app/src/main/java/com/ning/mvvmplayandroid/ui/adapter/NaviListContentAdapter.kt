package com.ning.mvvmplayandroid.ui.adapter

import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.databinding.ItemNavigationContentBinding
import com.ning.mvvmplayandroid.ui.activity.WebViewActivity
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import fule.com.playandroidkotlin.base.App.Companion.context
import fule.com.playandroidkotlin.ui.model.ArticlesModel
import fule.com.playandroidkotlin.ui.model.NavigationModel
import fule.com.playandroidkotlin.ui.util.AppConstant
import fule.com.playandroidkotlin.ui.util.CommonUtil
import fule.com.playandroidkotlin.ui.util.DisplayManager


/**
 *@作者: njb
 *@时间: 2020/1/15 12:40
 *@描述: 导航列表适配器
 */
class NaviListContentAdapter(data: List<NavigationModel.DataBean>?) :
    BaseQuickAdapter<NavigationModel.DataBean, BaseViewHolder>(
        R.layout.item_navigation_content,
        data
    ) {

    override fun convert(helper: BaseViewHolder, item: NavigationModel.DataBean) {
        val itemBinding = DataBindingUtil.bind<ItemNavigationContentBinding>(helper.itemView)!!
        itemBinding.navicontentBean = item
        itemBinding.articlesBean = item.articles
        itemBinding.executePendingBindings()
        // helper.setText(R.id.title_tv, item.name)
        val flowLayout: TagFlowLayout = helper.getView(R.id.flow_layout)
        val articles: List<ArticlesModel> = item.articles!!
        flowLayout.run {
            adapter = object : TagAdapter<ArticlesModel>(articles) {
                override fun getView(
                    parent: FlowLayout?,
                    position: Int,
                    article: ArticlesModel?
                ): View? {

                    val tv: TextView = LayoutInflater.from(parent?.context).inflate(
                        R.layout.flow_layout_tv,
                        flowLayout, false
                    ) as TextView

                    article ?: return null
                    val padding: Int = DisplayManager.dip2px(10F)!!
                    tv.setPadding(padding, padding, padding, padding)
                    tv.text = article.title
                    tv.setTextColor(CommonUtil.randomColor())
                    flowLayout.setOnTagClickListener { view, position1, parent ->
                        if (parent != null) {
                            startNavigationPager(view, position1, parent, articles)
                        }
                        true
                    }

                    return tv
                }
            }
        }
    }

    private fun startNavigationPager(
        view: View,
        position1: Int,
        parent: FlowLayout,
        articles: List<ArticlesModel>
    ) {
        val options = ActivityOptions.makeScaleUpAnimation(
            view,
            view.width / 2,
            view.height / 2,
            0,
            0
        )
        Intent(context, WebViewActivity::class.java).run {
            //跳转到网页的url
            putExtra(AppConstant.WEBVIEW_URL_KEY, articles[position1].link)
            //标题
            putExtra(AppConstant.WEBVIEW_TITLE_KEY, articles[position1].title)
            //id
            putExtra(AppConstant.WEBVIEW_ID_KEY, articles[position1].id)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(this, options.toBundle())
        }
    }
}