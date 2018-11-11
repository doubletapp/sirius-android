package com.doubletapp.sirius.di.fragment

import com.doubletapp.sirius.view.ProfileFragment
import com.doubletapp.sirius.view.SettingsFragment
import com.doubletapp.sirius.view.StoriesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
abstract class FragmentBindingModule {

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindProfileFragment(): ProfileFragment

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindStoriesFragment(): StoriesFragment
}