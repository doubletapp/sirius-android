package com.doubletapp.sirius.di.fragment

import com.doubletapp.sirius.di.fragment.FragmentViewModelModule
import com.doubletapp.sirius.view.trajectories.TrajectoriesGoalFragment
import com.doubletapp.sirius.view.trajectories.TrajectoriesYearFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
abstract class SubfragmentBindingModule {

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindTrajectoriesGoalFragment(): TrajectoriesGoalFragment

    @ContributesAndroidInjector(modules = [FragmentViewModelModule::class])
    abstract fun bindTrajectoriesYearFragment(): TrajectoriesYearFragment
}