package com.doubletapp.sirius.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class MainActivityAdapter constructor(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object {
        private const val POSITION_PROFILE = 0
        private const val POSITION_TRACES = 2
        private const val POSITION_COURSES = 3
        private const val POSITION_NEWS = 1
        private const val POSITION_SETTINGS = 4
    }

    override fun getItem(p0: Int): Fragment =
            when(p0) {
                POSITION_PROFILE -> ProfileFragment()
                POSITION_SETTINGS -> SettingsFragment()
                else -> Fragment()
            }

    override fun getCount(): Int = 5
}