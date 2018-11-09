package com.doubletapp.sirius.di.activity

import com.doubletapp.sirius.view.MainActivity
import com.doubletapp.sirius.view.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [ActivityViewModelModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ActivityViewModelModule::class])
    abstract fun bindSplashActivity(): SplashActivity
}