package com.doubletapp.sirius.di.fragment

import com.doubletapp.sirius.view.JustFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
abstract class FragmentBindingModule {

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindJustFragment(): JustFragment
}