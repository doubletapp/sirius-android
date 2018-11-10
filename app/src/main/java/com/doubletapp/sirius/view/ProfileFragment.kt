package com.doubletapp.sirius.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.module.AppGlideModule

import com.doubletapp.sirius.R
import com.doubletapp.sirius.base.BaseFragment
import com.doubletapp.sirius.base.KotlinAppGlideModule
import com.doubletapp.sirius.extensions.showFragment
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
                .load("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/36/3629ccc4e536041be8b7503d0e22d685c1d97def_full.jpg")
                .into(profileAvatar)
        profileChangeTest.setOnClickListener {
            showFragment(android.R.id.content,
                    TestChangeFragment(),
                    TestChangeFragment.TAG,
                    true)
        }
        profileRepeatTest.setOnClickListener {
            val fragment = SwipeTestLayout()
            showFragment(android.R.id.content,
                fragment,
                fragment.javaClass.simpleName,
                true)
        }
    }
}
