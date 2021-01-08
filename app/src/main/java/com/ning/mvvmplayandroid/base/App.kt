package fule.com.playandroidkotlin.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import com.tencent.bugly.crashreport.CrashReport
import fule.com.playandroidkotlin.ui.util.DisplayManager
import java.util.*
import kotlin.properties.Delegates
import com.tencent.bugly.crashreport.CrashReport.UserStrategy
import fule.com.playandroidkotlin.ui.util.CommonUtil.getProcessName


/**
 * 作者： njb
 * 时间： 2018/8/11 0011-上午 11:15
 * 描述： Application
 * 来源：
 */
@SuppressLint("Registered")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        DisplayManager.init(this)
        initBugly()
    }

    private fun initBugly() {
        val context = applicationContext
        // 获取当前包名
        val packageName = context.packageName
        // 获取当前进程名
        val processName = getProcessName(android.os.Process.myPid())
        // 设置是否为上报进程
        val strategy = UserStrategy(context)
        strategy.isUploadProcess = false || processName == packageName
        // 初始化Bugly
        CrashReport.initCrashReport(context, "0c5f5540ca", true, strategy)
        // 如果通过“AndroidManifest.xml”来配置APP信息，初始化方法如下
        // CrashReport.initCrashReport(context, strategy);
    }



    companion object {
        var context: Context by Delegates.notNull()
            private set
    }

}
