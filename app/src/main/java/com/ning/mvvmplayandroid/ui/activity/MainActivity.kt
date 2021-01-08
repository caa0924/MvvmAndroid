package com.ning.mvvmplayandroid.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.ning.mvvmplayandroid.R
import com.ning.mvvmplayandroid.ui.fragment.HomeFragment
import com.ning.mvvmplayandroid.ui.fragment.KnowledgeFragment
import com.ning.mvvmplayandroid.ui.fragment.NavigationFragment
import com.ning.mvvmplayandroid.ui.fragment.ProjectFragment
import com.ning.mvvmplayandroid.util.ToastUtils
import fule.com.playandroidkotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

/**
 * @作者: njb
 * @时间: 2020/1/13 12:51
 * @描述: 主界面
 */
open class MainActivity : BaseActivity() {
    private var lastExitTime: Long = 0
    private var index = 0
    private val fragmentList: MutableList<Fragment> =
        ArrayList()
    private val strings =
        arrayOf( R.string.home,  R.string.project, R.string.navigation, R.string.knowledge_tree)

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        initNav()
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)

            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        nav_view.setCheckedItem(R.id.favorites)
        nav_view.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }
    }

    private fun initNav() {

        val navListener = NavigationView.OnNavigationItemSelectedListener {
            ToastUtils.ToastShort(it.title.toString())
            when (it.itemId) {
                R.id.favorites -> {
                }
                R.id.todo -> {
                }
                R.id.night_mode -> {
                }
                R.id.setting -> {
                }
                R.id.logout -> {
                }
                R.id.about -> {
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        nav_view.run {
            setNavigationItemSelectedListener(navListener)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
//            R.id.backup -> Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show()
//            R.id.delete -> Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show()
//            R.id.settings -> Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show()
        }
        return true
    }


    override fun initView() {


        fragmentList.add(HomeFragment())
        fragmentList.add(KnowledgeFragment())
        fragmentList.add(NavigationFragment())
        fragmentList.add(ProjectFragment())
        viewPager!!.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return fragmentList[position]
            }

            override fun getItemCount(): Int {
                return fragmentList.size
            }
        }
        viewPager.offscreenPageLimit = 1
        val tabLayoutMediator = TabLayoutMediator(
            tab_layout,
            viewPager,
            TabConfigurationStrategy { tab: TabLayout.Tab, position: Int ->
                tab.text = getString(strings[position])
            }
        )
        tabLayoutMediator.attach()
        initToolBar()
    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setHomeButtonEnabled(false)
        iv_search.setImageResource(R.drawable.ic_baseline_search_24)
    }

    override fun addListener() {
        iv_search.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    SearchActivity::class.java
                )
            )
        })
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            return true
        } else if (KeyEvent.KEYCODE_BACK == keyCode) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastExitTime > 1500) {
                ToastUtils.ToastShort(this,getString(R.string.exit_hint))
                lastExitTime = currentTime
                return true
            } else {
                finish()
            }
     }
        return super.onKeyDown(keyCode, event)
    }
}