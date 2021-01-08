package fule.com.kotlindemo.base

import fule.com.playandroidkotlin.base.Model

/**
 * 作者： njb
 * 时间： 2016/12/27.13:56
 * 描述：
 * 来源：
 */
interface BaseView {
    /**
     * 显示dialog
     */
    fun showLoading()

    /**
     * 显示下载文件dialog
     */

    fun showLoadingFileDialog()

    /**
     * 隐藏下载文件dialog
     */

    fun hideLoadingFileDialog()

    /**
     * 下载进度
     * @param totalSize
     * @param downSize
     */

    fun onProgress(totalSize: Long, downSize: Long)

    /**
     * 隐藏 dialog
     */

    fun hideLoading()

    /**
     * 显示错误信息
     * @param msg
     */
    fun showError(msg: String)

    /**
     * 错误码
     */
    fun onErrorCode(model: Model)
}
