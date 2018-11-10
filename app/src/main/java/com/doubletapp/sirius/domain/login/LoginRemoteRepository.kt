package com.doubletapp.sirius.domain.login

import com.doubletapp.sirius.base.ApiErrorHandler
import com.doubletapp.sirius.base.BaseRemoteRepository
import com.doubletapp.sirius.base.BaseResponse
import io.reactivex.Single

class LoginRemoteRepository(apiErrorHandler: ApiErrorHandler,
                            private val loginRetrofit: LoginRetrofit) : BaseRemoteRepository(apiErrorHandler) {

    fun auth(vkId: String,
             vkToken: String,
             siriusId: String,
             siriusPassword: String,
             city: String): Single<LoginResponse> =
            loginRetrofit.auth(vkId, vkToken, siriusId, siriusPassword, city)
                    .compose(apiCompose())
}