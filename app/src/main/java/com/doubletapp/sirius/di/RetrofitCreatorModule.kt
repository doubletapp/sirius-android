package com.doubletapp.sirius.di

import android.content.Context
import com.doubletapp.sirius.BuildConfig
import com.doubletapp.sirius.base.ApiErrorHandler
import com.doubletapp.sirius.base.AuthorizationKeyValueStorage
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class RetrofitCreatorModule {

    companion object {
        private const val DUMMY_URL = "http://sirius.doubletapp.ru/"
        private const val TIMEOUT_SECONDS = 5L
        private const val LOGGING_INTERCEPTOR = "loggingInterceptor"
    }

    @Provides
    @Singleton
    fun provideApiErrorHandler(
        context: Context,
        authorizationKeyValueStorage: AuthorizationKeyValueStorage
    ): ApiErrorHandler =
        ApiErrorHandler(
            context = context,
            authorizationKeyValueStorage = authorizationKeyValueStorage
        )

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(DUMMY_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(@Named(LOGGING_INTERCEPTOR) loggingInterceptor: Interceptor,
                            authorizationKeyValueStorage: AuthorizationKeyValueStorage): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                var request = chain.request()
                val builder = request.newBuilder()
                var login = authorizationKeyValueStorage.isLogin()
                if (login)
                    builder.addHeader("Authorization", "Basic ${authorizationKeyValueStorage.getToken()}")
                else
                    builder.addHeader("Authorization", "Basic YWRtaW46MTIzcXdl")
                request = builder.build()
                chain.proceed(request)
            }
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .hostnameVerifier { hostname, session -> hostname.equals(session.peerHost, ignoreCase = true) }
            .build()

    @Provides
    @Singleton
    @Named(LOGGING_INTERCEPTOR)
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor()
            .setLevel(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            )
}