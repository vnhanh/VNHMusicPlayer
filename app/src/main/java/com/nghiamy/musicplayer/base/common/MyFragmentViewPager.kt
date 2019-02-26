package com.nghiamy.musicplayer.base.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MyFragmentViewPager(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager){
    private val titles = ArrayList<String>()
    private val fragments = ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment? {
        if(position >= fragments.size)
            return null
        return fragments.get(position)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    fun addItem(title:String, fragment:Fragment){
        titles.add(title)
        fragments.add(fragment)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if(position < titles.size) titles.get(position) else null
    }
}