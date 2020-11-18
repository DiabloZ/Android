package suhov.com.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import suhov.com.fragments.ListCrypto.ListCrypto

class StateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {

    var fragments: ArrayList<Fragment> = arrayListOf(
            ListCrypto(),
            EmptyFragment(),
            EmptyFragmentTwo()
    )
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}