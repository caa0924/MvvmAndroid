package fule.com.playandroidkotlin.base

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import butterknife.ButterKnife
import butterknife.Unbinder
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.ui.view.LoadingDialog
import fule.com.playandroidkotlin.ui.util.SettingUtil

/**
 * 基类 njb
 *
 * @param <P>
</P> */
abstract class BaseActivity : AppCompatActivity() {
    lateinit var context: Context
    var toast: Toast? = null
    protected var unbinder: Unbinder? = null

    protected abstract val layoutId: Int

    private var dialog: LoadingDialog? = null

    protected abstract fun initView()

    protected abstract fun addListener()
    /**
     * theme color
     */
    private var mThemeColor: Int = SettingUtil.getColor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (layoutId != 0) {
            setContentView(layoutId)
        }
        unbinder = ButterKnife.bind(this)
        context = this
        initView()
        addListener()
    }

    /**
     * 打开Activity
     *
     * @param cls
     */
    fun startA(cls: Class<*>) {


        val intent = Intent(context, cls)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        initColor()
    }

    public override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()

        if (unbinder != null) {
            unbinder!!.unbind()
        }

    }

    fun initToolBar(title: String): Toolbar? {

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            val toolbarTitle = findViewById<TextView>(R.id.toolbar_title)
            toolbarTitle.text = title
        }
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowTitleEnabled(false)
        }
        return toolbar
    }

    fun initToolBarAsHome(title: String): Toolbar? {

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            val toolbarTitle = findViewById<TextView>(R.id.toolbar_title)
            toolbarTitle.text = title
        }
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false)
            actionBar.setDisplayShowTitleEnabled(false)
        }
        return toolbar
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Fragment 逐个出栈
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    open fun initColor() {
        mThemeColor = if (!SettingUtil.getIsNightMode()) {
            SettingUtil.getColor()
        } else {
            ContextCompat.getColor(context,R.color.colorPrimary)
        }
        if (this.supportActionBar != null) {
            this.supportActionBar?.setBackgroundDrawable(ColorDrawable(mThemeColor))
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.statusBarColor = CircleView.shiftColorDown(mThemeColor)
//            // 最近任务栏上色
//            val tDesc = ActivityManager.TaskDescription(
//                    getString(R.string.app_name),
//                    BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher),
//                    mThemeColor)
//            setTaskDescription(tDesc)
          /*  if (SettingUtil.getNavBar()) {
                window.navigationBarColor = CircleView.shiftColorDown(mThemeColor)
            } else {
                window.navigationBarColor = Color.BLACK
            }*/
        }
    }

    /**
     * @param s
     */
    fun showtoast(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
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



    /**
     * 显示加载动画
     */
    open fun showLoadingDialog() {
        if (dialog != null && dialog!!.isShowing()) {
            return
        }
        if (null == dialog) {
            dialog = LoadingDialog(context, R.style.dialogActive)
        }
        dialog!!.setCancelable(false)
        dialog!!.show()
    }

    /**
     * 隐藏加载动画
     */
    open fun closeLoadingDialog() {
        if (dialog != null && dialog!!.isShowing()) {
            dialog!!.dismiss()
        }
    }



    fun addFragmentToActivity(fragmentManager: FragmentManager,
                              fragment: Fragment, frameId: Int) {
        val transaction = fragmentManager.beginTransaction()
        if (!fragment.isAdded)
            transaction.add(frameId, fragment)
        fragmentManager.fragments.filter { it.id == fragment.id }.map { transaction.hide(it) }
        transaction.show(fragment)
        transaction.commit()
    }

}
