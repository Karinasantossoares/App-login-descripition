package com.example.app_description_apiary.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.app_description_apiary.ui.fragment.DetailsProfileFragment

class ScreenPagerAdapter( fm: Fragment, private val  listFragments : List<Fragment>) :FragmentStateAdapter(fm) {

    override fun getItemCount(): Int = listFragments.size

    override fun createFragment(position: Int): Fragment {
        return listFragments[position]
    }
}
