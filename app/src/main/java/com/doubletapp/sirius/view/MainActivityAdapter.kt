package com.doubletapp.sirius.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.doubletapp.sirius.view.trajectories.TrajectoriesFragment

class MainActivityAdapter constructor(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object {
        private const val POSITION_PROFILE = 0
        private const val POSITION_TRACES = 2
        private const val POSITION_NEWS = 1
        private const val POSITION_SETTINGS = 3
    }

    override fun getItem(p0: Int): Fragment =
            when (p0) {
                POSITION_PROFILE -> ProfileFragment()
                POSITION_SETTINGS -> SettingsFragment()
                POSITION_NEWS -> FeedFragment()
                POSITION_TRACES -> TrajectoriesFragment.newInstance()
                else -> Fragment()
            }

    override fun getCount(): Int = 4
}