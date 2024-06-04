package com.bangkitacademy.harvesthero.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter internal constructor(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = HomeFragment()
            1 -> fragment = HomeFragment()
            2 -> fragment = HomeFragment()
        }
        return fragment as Fragment    }

    override fun getItemCount(): Int {
        return 3
    }

}