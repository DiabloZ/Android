package com.suhov.memappwocompose.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.suhov.memappwocompose.R
import com.suhov.memappwocompose.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabs()
    }

    private fun setUpTabs() {
        val VP = ViewPagerAdapter(childFragmentManager, lifecycle)
        VP.addFragment(LastsFragment(),resources.getString(R.string.lasts),resources.getDrawable(R.drawable.lasts, null))
        VP.addFragment(HotsFragment(),resources.getString(R.string.hots),resources.getDrawable(R.drawable.hots, null))
        VP.addFragment(RandomFragment(),resources.getString(R.string.random),resources.getDrawable(R.drawable.random, null))
        VP.addFragment(FavoritesFragment(),resources.getString(R.string.favorites),resources.getDrawable(R.drawable.favorites, null))

        view_pager.adapter = VP
        TabLayoutMediator(tab_layout, view_pager
        ){tab, position ->
            tab.text = resources.getStringArray(R.array.tabs_names)[position]
        }.attach()


    }
}