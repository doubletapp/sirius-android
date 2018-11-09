package com.doubletapp.sirius.di.activity

import android.arch.lifecycle.ViewModelProviders
import com.doubletapp.sirius.di.ViewModelFactory
import com.doubletapp.sirius.presentation.MainViewModel
import com.doubletapp.sirius.presentation.survey.SurveyViewModel
import com.doubletapp.sirius.view.MainActivity
import com.doubletapp.sirius.view.survey.SurveyActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityViewModelModule {

    @Provides
    fun provideMainViewModel(viewModelFactory: ViewModelFactory, mainActivity: MainActivity): MainViewModel =
            ViewModelProviders.of(mainActivity, viewModelFactory).get(MainViewModel::class.java)

    @Provides
    fun provideSurveyViewModel(viewModelFactory: ViewModelFactory, surveyActivity: SurveyActivity): SurveyViewModel =
            ViewModelProviders.of(surveyActivity, viewModelFactory).get(SurveyViewModel::class.java)
}