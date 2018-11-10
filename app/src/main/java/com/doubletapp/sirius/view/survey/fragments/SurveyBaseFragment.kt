package com.doubletapp.sirius.view.survey.fragments

import com.doubletapp.sirius.base.BaseFragment
import com.doubletapp.sirius.presentation.survey.SurveyViewModel

abstract class SurveyBaseFragment : BaseFragment() {

    lateinit var model: SurveyViewModel

    abstract fun onNextPressed() : Boolean
}
