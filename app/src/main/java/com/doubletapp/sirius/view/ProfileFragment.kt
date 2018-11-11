package com.doubletapp.sirius.view


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.doubletapp.sirius.R
import com.doubletapp.sirius.base.BaseFragment
import com.doubletapp.sirius.extensions.showFragment
import com.vk.sdk.api.model.VKApiUserFull
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

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
        (activity as MainActivity).mainViewModel.userInfo.observe(this, profileObserver)
        (activity as MainActivity).mainViewModel.getUser()
    }

    private val profileObserver = Observer { user: VKApiUserFull? ->
        if (user != null) {
            Glide.with(this)
                    .load(user.photo_200)
                    .into(profileAvatar)
            profileName.text = "${user.first_name} ${user.last_name}"
            profileCity.text = user.city.title
        }
    }
}
