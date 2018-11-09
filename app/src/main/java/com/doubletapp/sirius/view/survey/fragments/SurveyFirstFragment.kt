package com.doubletapp.sirius.view.survey.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doubletapp.sirius.R
import com.doubletapp.sirius.base.BaseFragment
import com.doubletapp.sirius.presentation.survey.SurveyViewModel
import com.vk.sdk.api.model.VKApiUser
import javax.inject.Inject

class SurveyFirstFragment() : BaseFragment() {

    var userInfoObserver = Observer<VKApiUser> {
        it?.let { user ->
            Log.d("!!!", "user $user")
            //val sdf = SimpleDateFormat("", Locale.getDefault())
            //surveyFirstDateValue.text = sdf.format(user.fields)
        }
    }

    @Inject
    lateinit var model: SurveyViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_survey_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model.userInfo.observe(this, userInfoObserver)
        model.getInfo()
    }

}
