package suhov.com.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import suhov.com.R

class MainFragment : Fragment() {
    var TAG:String = "MainFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager2WithFragments()
    }

    private fun initViewPager2WithFragments() {
        val thisClass:String = (object : Any(){}.javaClass.enclosingMethod?.name).toString();

        val viewPager: ViewPager2 = requireView().findViewById(R.id.viewpager)
        val adapter = StateAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter
        val tabLayout:TabLayout = requireView().findViewById(R.id.tab_layout)
        val names:ArrayList<String> = arrayListOf("List", "Empty", "Empty_Two")
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = names.get(position)
            Log.i(TAG, "initViewPager2: test - " + thisClass )
        }.attach()
    }
}