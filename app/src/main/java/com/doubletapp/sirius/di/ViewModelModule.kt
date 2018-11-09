package com.doubletapp.sirius.di

import android.arch.lifecycle.ViewModel
import com.doubletapp.sirius.presentation.JustViewModel
import com.doubletapp.sirius.presentation.MainViewModel
import com.doubletapp.sirius.presentation.survey.SurveyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(JustViewModel::class)
    abstract fun bindJustViewModel(justViewModel: JustViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SurveyViewModel::class)
    abstract fun bindSurveyViewModel(surveyViewModel: SurveyViewModel): ViewModel
}