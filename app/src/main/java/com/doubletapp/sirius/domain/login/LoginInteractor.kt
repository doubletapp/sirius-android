package com.doubletapp.sirius.domain.login

import android.content.Context
import com.doubletapp.sirius.base.AuthorizationKeyValueStorage
import com.doubletapp.sirius.view.SplashActivity
import com.vk.sdk.VKSdk

class LoginInteractor
constructor(private val context: Context,
            private val authorizationKeyValueStorage: AuthorizationKeyValueStorage) {

    fun logout() {
        VKSdk.logout()
        SplashActivity.start(context)
    }
}