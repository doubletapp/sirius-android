package com.doubletapp.sirius.di.fragment

import android.arch.lifecycle.ViewModelProviders
import com.doubletapp.sirius.di.ViewModelFactory
import com.doubletapp.sirius.presentation.JustViewModel
import com.doubletapp.sirius.view.JustFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentViewModelModule {

    @Provides
    fun provideJustViewModel(viewModelFactory: ViewModelFactory, justFragment: JustFragment): JustViewModel =
            ViewModelProviders.of(justFragment, viewModelFactory).get(JustViewModel::class.java)
}