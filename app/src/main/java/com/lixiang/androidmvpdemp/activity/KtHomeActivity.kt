package com.lixiang.androidmvpdemp.activity

import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import com.common.base.BaseActivity
import com.google.android.material.tabs.TabLayout
import com.guomin.niubi.fragment.KtMineFragment
import com.lixiang.androidmvpdemp.R
import com.lixiang.androidmvpdemp.widget.ImmersionTitleView


/**
 * Copyright (C),  国民健康科技有限公司

 * @ProjectName: 脉诊
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2019/12/23 14:37
 */
class KtHomeActivity : BaseActivity() {


    @BindView(R.id.immersionTitleView)
    lateinit var immersionTitleView: ImmersionTitleView

    @BindView(R.id.tabLayout)
    lateinit var tabLayout: TabLayout

    @BindView(R.id.viewPager)
    lateinit var viewPager: ViewPager

    @BindView(R.id.button5)
    lateinit var button5: Button


    private var mFragments = mutableListOf<Fragment>()
    var title = arrayOf("首页", "我的", "设置")


    override fun initView() {
        title.forEach {

            mFragments.add(KtMineFragment.getInsytance(it))
//            mFragments.add(ClassLoaderLi.getFragment(it))
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

