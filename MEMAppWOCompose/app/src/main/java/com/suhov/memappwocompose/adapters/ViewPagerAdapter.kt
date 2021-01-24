package com.suhov.memappwocompose.adapters

import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.suhov.memappwocompose.fragments.FavoritesFragment
import com.suhov.memappwocompose.fragments.HotsFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
        FragmentStateAdapter(fragmentManager, lifecycle) {
    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()
    private val mFragmentIconList = ArrayList<Drawable>()



    override fun getItemCount(): Int = mFragmentList.size

    override fun createFragment(position: Int): Fragment = mFragmentList[position]

    fun addFragment(fragment: Fragment, titleFragment: String, icon: Drawable? = null){
        mFragmentList.add(fragment)
        mFragmentTitleList.add(titleFragment)
        if (icon != null)
            mFragmentIconList.add(icon)
    }
    fun getTitleItem(position: Int):String =  mFragmentTitleList[position]
    fun getIconItem(position: Int):Drawable =  mFragmentIconList[position]
}