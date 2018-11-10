package com.doubletapp.sirius.domain.login

import android.content.Context
import com.doubletapp.sirius.base.ApiErrorHandler
import com.doubletapp.sirius.base.AuthorizationKeyValueStorage
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class LoginModule {

    @Provides
    @Singleton
    fun provideLoginInteractor(context: Context,
                               authorizationKeyValueStorage: AuthorizationKeyValueStorage,
                               loginRemoteRepository: LoginRemoteRepository) =
            LoginInteractor(context, authorizationKeyValueStorage, loginRemoteRepository)

    @Provides
    @Singleton
    fun provideLoginRemoteRepository(apiErrorHandler: ApiErrorHandler,
                                     retrofit: LoginRetrofit) =
            LoginRemoteRepository(apiErrorHandler, retrofit)

    @Provides
    @Singleton
    fun provireLoginRetrofit(retrofit: Retrofit) =
            retrofit.create(LoginRetrofit::class.java)
}