package com.doubletapp.sirius.view.survey.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doubletapp.sirius.R

class SurveyFormatFragment : SurveyBaseFragment() {
    override fun onNextPressed(): Boolean {
        return true
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_survey_format, container, false)
    }

}
