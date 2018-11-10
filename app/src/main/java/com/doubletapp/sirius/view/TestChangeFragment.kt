package com.doubletapp.sirius.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.doubletapp.sirius.R
import kotlinx.android.synthetic.main.fragment_test_change.*

class TestChangeFragment : Fragment() {

    companion object {
        const val TAG = "TestChangeFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test_change, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        testChangeToolbar.setNavigationOnClickListener { activity?.onBackPressed() }
    }
}
