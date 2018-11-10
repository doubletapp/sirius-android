package com.doubletapp.sirius.domain.login

import android.content.Context
import com.doubletapp.sirius.base.AuthorizationKeyValueStorage
import com.doubletapp.sirius.base.BaseResponse
import com.doubletapp.sirius.view.SplashActivity
import com.vk.sdk.VKSdk
import io.reactivex.Single

class LoginInteractor
constructor(private val context: Context,
            private val authorizationKeyValueStorage: AuthorizationKeyValueStorage,
            private val loginRemoteRepository: LoginRemoteRepository) {

    fun logout() {
        VKSdk.logout()
        authorizationKeyValueStorage.logout()
        SplashActivity.start(context)
    }

    fun auth(vkId: String,
             vkToken: String,
             siriusId: String,
             siriusPassword: String,
             city: String): Single<LoginResponse> =
            loginRemoteRepository.auth(vkId, vkToken, siriusId, siriusPassword, city)
}