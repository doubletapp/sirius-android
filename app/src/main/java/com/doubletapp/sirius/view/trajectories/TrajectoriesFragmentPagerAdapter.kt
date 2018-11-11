package com.doubletapp.sirius.view.trajectories

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TrajectoriesFragmentPagerAdapter(

        fragmentManager: FragmentManager

) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment = when (position) {
        POSITION_GOAL -> TrajectoriesGoalFragment.newInstance()
        else -> TrajectoriesYearFragment.newInstance()
    }

    override fun getPageTitle(position: Int): CharSequence? = when (position) {
        POSITION_GOAL -> "До цели"
        else -> "За год"
    }

    override fun getCount(): Int = SIZE

    companion object {

        private const val SIZE = 2

        private const val POSITION_GOAL = 0
    }
}