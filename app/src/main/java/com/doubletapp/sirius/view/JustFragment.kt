package com.doubletapp.sirius.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doubletapp.sirius.R
import com.doubletapp.sirius.base.BaseFragment
import com.doubletapp.sirius.presentation.JustViewModel

import javax.inject.Inject

class JustFragment : BaseFragment() {

    @Inject
    lateinit var justViewModel: JustViewModel

    companion object {
        const val TAG = "JustFragment"
        fun newInstance(): JustFragment = JustFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_just, container, false)
    }
}
