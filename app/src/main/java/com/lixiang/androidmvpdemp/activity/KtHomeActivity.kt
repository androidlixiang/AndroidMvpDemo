package com.lixiang.androidmvpdemp.activity

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
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
    private var viewPager: ViewPager? = null
    private var mFragments = mutableListOf<Fragment>()
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
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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


    class UseFragmentPagerAdapter(fm: FragmentManager, var fragments: MutableList<Fragment>, var titles: Array<String>) : FragmentPagerAdapter(fm) {
        override fun getItem(p0: Int): Fragment {

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

