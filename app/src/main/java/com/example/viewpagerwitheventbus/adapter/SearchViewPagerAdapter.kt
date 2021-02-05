package com.example.viewpagerwitheventbus.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.viewpagerwitheventbus.fragment.FirstFragment
import com.example.viewpagerwitheventbus.R
import com.example.viewpagerwitheventbus.fragment.SecondFragment
import com.example.viewpagerwitheventbus.fragment.ThirdFragment

class SearchViewPagerAdapter(
    private val context: Context?,
    fragmentManager: FragmentManager
) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            else -> ThirdFragment()
        }
    }

    override fun getCount() = 3

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context?.getString(R.string.premier_league)
            1 -> context?.getString(R.string.europa_league)
            2 -> context?.getString(R.string.champions_league)
            else -> ""
        }
    }
}