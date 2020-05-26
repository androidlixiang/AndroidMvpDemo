package com.lixiang.androidmvpdemp.activity

import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.common.base.BaseActivity
import com.lixiang.androidmvpdemp.R
import com.lixiang.androidmvpdemp.fragment.KtHomeFragment


/**
 * Copyright (C),  国民健康科技有限公司

 * @ProjectName: 脉诊
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2019/12/23 14:37
 */
class KtHomeActivity : BaseActivity() {

    private var tabLayout: TabLayout? = null
    private var viewPager: androidx.viewpager.widget.ViewPager? = null
    private var mFragments = mutableListOf<androidx.fragment.app.Fragment>()
    var title = arrayOf("首页", "我的", "设置")


    override fun initView() {
        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        title.forEach {
            mFragments.add(KtHomeFragment.getInsytance(it))
        }
        val useFragmentPagerAdapter = UseFragmentPagerAdapter(supportFragmentManager, mFragments, title)
        viewPager?.run {
            adapter = useFragmentPagerAdapter
            addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(p0: Int) {
                }

                override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                }

                override fun onPageSelected(p0: Int) {
                }
            })
            offscreenPageLimit = mFragments.size

        }

        tabLayout?.run {
            setupWithViewPager(viewPager)
            tabMode = TabLayout.GRAVITY_FILL
        }
    }


    override fun getLayoutResId(): Int {
        return R.layout.activity_home


    }


    class UseFragmentPagerAdapter(fm: androidx.fragment.app.FragmentManager, var fragments: MutableList<androidx.fragment.app.Fragment>, var titles: Array<String>) : androidx.fragment.app.FragmentPagerAdapter(fm) {
        override fun getItem(p0: Int): androidx.fragment.app.Fragment {

            return fragments.get(p0)
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles.get(position)
        }

    }


}

