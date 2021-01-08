package com.ning.mvvmplayandroid.base


import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.ning.mvvmplayandroid.R

/**
 * @author njb
 */
@Suppress("DEPRECATION")
abstract class BaseFragment : Fragment() {

    internal var context: Context? = null
    private var dialog: ProgressDialog? = null

    // 控件是否初始化完成
    private var isViewCreated: Boolean = false

    // 当前fragment是否加载过数据,如加载过数据，则不再加载
    private var isLoadCompleted: Boolean = false
    //是不是可见
    private var isUIVisible: Boolean = false



    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun getLayoutId(): Int


    // 懒加载,强制子类重写
    abstract fun loadData()

    abstract fun initView()

    abstract fun addListener()


    abstract fun setTitle()


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isUIVisible = isVisibleToUser
        if (isVisibleToUser && isViewCreated && isUIVisible && !isLoadCompleted) {
            isLoadCompleted = true
            loadData()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (isViewCreated && isUIVisible) {

            loadData()
            isLoadCompleted = true
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(getLayoutId(), container, false)
/*        presenter = createPresenter()
        isViewCreated = true

        initView()
        addListener()*/
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
        initView()
        addListener()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        isUIVisible = !hidden
        isLoadCompleted = !hidden
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()


    }

    /**
     * 打开指定的activity
     *
     * @param cls
     */
    fun startA(cls: Class<*>) {
        val intent = Intent(context, cls)
        startActivity(intent)
    }

    fun setStatusBar(view: View?) {
        if (view == null) {
            return
        }

    }


    /**
     * toast
     *
     * @param msg
     */
    fun showtoast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * 显示加载动画
     */
    fun showLoadingDialog() {
        if (dialog != null && dialog!!.isShowing) {
            return
        }
        if (null == dialog) {
            dialog = ProgressDialog(context, R.style.AlertDialogStyle)
        }
        dialog!!.setCancelable(false)
        dialog!!.show()

    }

    /**
     * 隐藏加载动画
     */
    fun closeLoadingDialog() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }

    /**
     * 通过资源res获得view
     *
     * @param res
     * @return
     */
    fun getViewByRes(@LayoutRes res: Int): View {
        return LayoutInflater.from(context).inflate(res, null)
    }


    /**
     * 获得TextView 的文本
     *
     * @param tv
     * @return
     */
    fun getTV(tv: TextView?): String {
        return tv?.text?.toString()?.trim { it <= ' ' } ?: ""
    }


    private fun showFileDialog() {}

    private fun hideFileDialog() {}




}
