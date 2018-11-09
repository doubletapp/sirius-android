package com.doubletapp.sirius

import com.doubletapp.sirius.di.DaggerApplicationComponent
import com.vk.sdk.VKSdk
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class SiriusApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.builder().create(this)

    override fun onCreate() {
        super.onCreate()
        VKSdk.initialize(this)
    }
}