package com.doubletapp.sirius.di.fragment

import com.doubletapp.sirius.view.JustFragment
import com.doubletapp.sirius.view.ProfileFragment
import com.doubletapp.sirius.view.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
abstract class FragmentBindingModule {

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindJustFragment(): JustFragment

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindProfileFragment(): ProfileFragment
}