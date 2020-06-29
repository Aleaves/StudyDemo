package com.sdk.simpleeyes.ui.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

open class BaseFragmentItemAdapter(fragmentManager: FragmentManager,private val fragments : MutableList<Fragment>,private val titles : MutableList<String>): FragmentPagerAdapter(fragmentManager){

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]

}