package fule.com.playandroidkotlin.base

/**
 * 作者： njb
 * 时间： 2016/12/27.13:56
 * 描述：
 * 来源：
 */
class BaseListModel<T> {
    var data: List<T>? = null
    var errorCode: Int = 0
    var errorMsg: String? = null
}