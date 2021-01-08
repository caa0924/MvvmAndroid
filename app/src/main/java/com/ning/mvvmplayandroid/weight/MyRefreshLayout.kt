package com.ning.mvvmplayandroid.weight

import android.content.Context
import android.util.AttributeSet
import com.ning.mvvmplayandroid.R
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader

/**
 *@author: njb
 *@date:   2020/2/10 0010 11:42
 *@desc:
 */
class MyRefreshLayout : SmartRefreshLayout {
    constructor(context: Context) : super(context) {
        val header = ClassicsHeader(context)
            .setEnableLastTime(false)
            .setPrimaryColor(context.resources.getColor(R.color.c999999))
            .setAccentColor(context.resources.getColor(R.color.white))
        setRefreshHeader(header)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val header = ClassicsHeader(context)
            .setEnableLastTime(false)
            .setPrimaryColor(context.resources.getColor(R.color.c999999))
            .setAccentColor(context.resources.getColor(R.color.white))
        setRefreshHeader(header)
        //        setRefreshHeader(new MyHeader(context));
    }
}