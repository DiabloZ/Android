package com.suhov.memappwocompose.presentation.ui.mem.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.suhov.memappwocompose.R
import com.suhov.memappwocompose.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_main.*

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
        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(LastsFragment(), getStringFromResource(R.string.lasts), getDrawableFromResource(R.drawable.lasts))
        viewPagerAdapter.addFragment(HotsFragment(), getStringFromResource(R.string.hots), getDrawableFromResource(R.drawable.hots))
        viewPagerAdapter.addFragment(RandomFragment(), getStringFromResource(R.string.random), getDrawableFromResource(R.drawable.random))
        viewPagerAdapter.addFragment(FavoritesFragment(), getStringFromResource(R.string.favorites), getDrawableFromResource(R.drawable.favorites))
        view_pager.adapter = viewPagerAdapter

        TabLayoutMediator(tab_layout, view_pager){tab, position ->
            tab.text = viewPagerAdapter.getTitleItem(position)
            tab.icon = viewPagerAdapter.getIconItem(position)
        }.attach()
    }

    private fun getDrawableFromResource(id: Int):Drawable = ContextCompat.getDrawable(requireActivity(), id) as Drawable
    private fun getStringFromResource(id: Int):String = resources.getString(id)
}