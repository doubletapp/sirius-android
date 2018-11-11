package com.doubletapp.sirius.view.trajectories

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doubletapp.sirius.R
import com.doubletapp.sirius.base.BaseFragment
import com.doubletapp.sirius.presentation.trajectories.TrajectoriesViewModel
import kotlinx.android.synthetic.main.fragment_trajectories_year.*
import javax.inject.Inject

class TrajectoriesYearFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: TrajectoriesViewModel

    private val adapter = TrajectoriesAdapter()

    companion object {

        const val TAG = "TrajectoriesYearFragment"

        fun newInstance() = TrajectoriesYearFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_trajectories_year, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentTrajectoriesYear.adapter = adapter
        viewModel.yearTrajectories.observe(this, Observer { list ->
            list?.let { adapter.updateItems(list) }
        })
    }
}