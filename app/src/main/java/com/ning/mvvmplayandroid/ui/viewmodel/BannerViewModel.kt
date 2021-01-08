package com.ning.mvvmplayandroid.ui.viewmodel

import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.ning.mvvmplayandroid.base.BaseViewModel
import com.ning.mvvmplayandroid.ui.repository.BannerRepository
import com.ning.mvvmplayandroid.ui.view.CompletedListener
import com.youth.banner.Banner
import fule.com.playandroidkotlin.base.BaseListModel
import fule.com.playandroidkotlin.ui.model.BannerModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

/**
 *@author: njb
 *@date:   2020/2/15 0015 18:48
 *@desc:
 */
class BannerViewModel(
    private var banner: Banner,
    private var completedListener: CompletedListener
) :BaseViewModel<BaseListModel<BannerViewModel>>(){
    private var observer: Observer<BaseListModel<BannerModel>>? = null
    private var repository = BannerRepository()
    private var bannerModel: List<BannerModel>? = null

    fun getBannerList(){
        viewModelScope.launch {
            try {
                observer = object : Observer<BaseListModel<BannerModel>> {
                    override fun onComplete() {
                        completedListener.onCompleted()
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: BaseListModel<BannerModel>) {
                        if (t.errorCode == 0) {
                            t.data.let {
                                val list = ArrayList<String>()
                                for (m in t.data!!) {
                                    list.add(m.imagePath!!)
                                }
                                bannerModel = t.data
                                banner!!.update(list)
                            }
                        }

                    }

                    override fun onError(e: Throwable) {
                        completedListener.onCompleted()
                    }

                }
                repository.getBannerList(observer as Observer<BaseListModel<BannerModel>>)
            }catch (e: Exception){
                e.printStackTrace()
            }

        }
    }

    override fun loadData() {

    }
}