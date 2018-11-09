package com.doubletapp.sirius.di

import android.content.Context
import android.content.SharedPreferences
import com.doubletapp.sirius.SiriusApplication
import com.doubletapp.sirius.base.AuthorizationKeyValueStorage
import com.doubletapp.sirius.di.activity.ActivityBindingModule
import com.doubletapp.sirius.di.fragment.FragmentBindingModule
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ActivityBindingModule::class, FragmentBindingModule::class])
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: SiriusApplication): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideAuthorizationKeyValueStorage(@Named(AuthorizationKeyValueStorage.AUTHORIZATION_DATA) sharedPreferences: SharedPreferences)
            : AuthorizationKeyValueStorage =
        AuthorizationKeyValueStorage(sharedPreferences)

    @Provides
    @Singleton
    @Named(AuthorizationKeyValueStorage.AUTHORIZATION_DATA)
    fun provideAuthorizationSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(AuthorizationKeyValueStorage.AUTHORIZATION_DATA, Context.MODE_PRIVATE)
}