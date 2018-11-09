package com.doubletapp.sirius.domain.just

import com.doubletapp.sirius.base.ApiErrorHandler
import com.doubletapp.sirius.base.BaseRemoteRepository

class JustRemoteRepository(
    apiErrorHandler: ApiErrorHandler,
    private val justRetrofit: JustRetrofit
) : BaseRemoteRepository(apiErrorHandler) {

}