package com.sdk.simpleeyes.ui.splash.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sdk.simpleeyes.ui.splash.SloganFragment

class SplashVideoFragmentAdapter(var mFragmentList: MutableList<SloganFragment>, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment = mFragmentList[position]

    override fun getCount(): Int = mFragmentList.size

}