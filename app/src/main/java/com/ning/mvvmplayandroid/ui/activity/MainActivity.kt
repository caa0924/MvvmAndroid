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
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
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
    private var fragmentTag: String? = null
    private var mCurrentFragment: Fragment? = null
    private val fragmentList: MutableList<Fragment> =
        ArrayList()
    private val fragmentNames = arrayOf(
        HomeFragment::class.java.name, ProjectFragment::class.java.name,
        NavigationFragment::class.java.name, KnowledgeFragment::class.java.name
    )
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
        initNavBottom()
        bottomNav()
    }
    private fun initNavBottom() {
        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> index = 0

                R.id.project -> index = 1
                R.id.navigation -> index = 2
                R.id.knowledge_tree -> index = 3

            }
            bottomNav()
            true
        }
        navigation_bottom.run {
            labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_AUTO
            setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        }
    }
    private fun bottomNav() {
        toolbar.title = getString(
            if (index == 0) {
                R.string.app_name
            } else {
                strings[index]
            }
        )
        fragmentTag = fragmentNames[index]
        val fragment = getFragmentByTag(fragmentTag!!)
        showFragment(mCurrentFragment, fragment, fragmentTag!!)
    }
    private fun getFragmentByTag(name: String): Fragment {
        var fragment = supportFragmentManager.findFragmentByTag(name)
        if (fragment != null) {
            return fragment
        } else {
            try {
                fragment = Class.forName(name).newInstance() as Fragment
            } catch (e: Exception) {
                fragment = HomeFragment()
            }
        }
        return fragment!!
    }

    private fun showFragment(from: Fragment?, to: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        if (from == null) {
            if (to.isAdded) {
                transaction.show(to)
            } else {
                transaction.add(R.id.container, to, tag)
            }
        } else {
            if (to.isAdded) {
                transaction.hide(from).show(to)
            } else {
                transaction.hide(from).add(R.id.container, to, tag)
            }
        }
        transaction.commit()
        mCurrentFragment = to
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