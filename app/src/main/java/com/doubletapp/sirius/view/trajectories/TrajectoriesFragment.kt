package com.doubletapp.sirius.view.trajectories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doubletapp.sirius.R
import com.doubletapp.sirius.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_trajectories.*

class TrajectoriesFragment : BaseFragment() {

    companion object {

        const val TAG = "TrajectoriesFragment"

        fun newInstance() = TrajectoriesFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_trajectories, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fragmentTrajectoriesViewPager.adapter = TrajectoriesFragmentPagerAdapter(childFragmentManager)
        fragmentTrajectoriesTabLayout.setupWithViewPager(fragmentTrajectoriesViewPager)
    }
}