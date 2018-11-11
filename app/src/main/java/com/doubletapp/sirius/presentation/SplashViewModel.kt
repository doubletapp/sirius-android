package com.doubletapp.sirius.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.doubletapp.sirius.base.AuthorizationKeyValueStorage
import com.doubletapp.sirius.base.BaseViewModel
import com.doubletapp.sirius.domain.login.LoginInteractor
import java.nio.charset.StandardCharsets
import java.util.*
import javax.inject.Inject

class SplashViewModel
@Inject
constructor(
           private val loginInteractor: LoginInteractor,
           private val authorizationKeyValueStorage: AuthorizationKeyValueStorage
) : BaseViewModel() {

    private val loginData = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> get() = loginData

    fun login(vkId: String,
              vkToken: String,
              siriusId: String,
              siriusPassword: String,
              city: String,
              context: Context) {
        disposables.add(
                loginInteractor.auth(vkId, vkToken, siriusId, siriusPassword, city)
                        .subscribe({
                            val array = "${it.vk_id}:${it.auth_token}".toByteArray(StandardCharsets.UTF_8)
                            authorizationKeyValueStorage.login(Base64.getEncoder().encodeToString(array))
                            loginData.postValue(true)
                            //MainActivity.start(context)
                        }, {})
        )
    }

    fun isLoggedIn(): Boolean = authorizationKeyValueStorage.isLogin()
    fun isShowTest(): Boolean = authorizationKeyValueStorage.isTestPassed()
}