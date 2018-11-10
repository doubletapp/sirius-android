package com.doubletapp.sirius.domain.login

import android.content.Context
import com.doubletapp.sirius.base.AuthorizationKeyValueStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LoginModule {

    @Provides
    @Singleton
    fun provideLoginInteractor(context: Context,
                               authorizationKeyValueStorage: AuthorizationKeyValueStorage) =
            LoginInteractor(context, authorizationKeyValueStorage)
}