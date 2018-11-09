package com.doubletapp.sirius.di

import com.doubletapp.sirius.SiriusApplication
import com.doubletapp.sirius.domain.just.JustModule
import com.doubletapp.sirius.domain.main.MainModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ApplicationModule::class,
        RetrofitCreatorModule::class, ViewModelModule::class, MainModule::class,
        JustModule::class]
)
interface ApplicationComponent : AndroidInjector<SiriusApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<SiriusApplication>()
}