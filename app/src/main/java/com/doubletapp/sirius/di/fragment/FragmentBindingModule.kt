package com.doubletapp.sirius.di.fragment

import com.doubletapp.sirius.view.ProfileFragment
import com.doubletapp.sirius.view.SettingsFragment
import com.doubletapp.sirius.view.SwipeTestLayout
import com.doubletapp.sirius.view.survey.fragments.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
abstract class FragmentBindingModule {

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindSurveyFirstFragment(): SurveyFirstFragment

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindSurveySecondFragment(): SurveySecondFragment

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindSurveyThirdFragment(): SurveyThirdFragment

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindProfileFragment(): ProfileFragment

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindSwipeFragment(): SwipeTestLayout

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindEventFragment(): SurveyEventFragment

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindAchievementsFragment(): AchievementsSurveyFragment

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindFormatFragment(): SurveyFormatFragment
}