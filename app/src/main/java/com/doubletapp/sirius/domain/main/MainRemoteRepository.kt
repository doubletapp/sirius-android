package com.doubletapp.sirius.domain.main

import com.doubletapp.sirius.base.ApiErrorHandler
import com.doubletapp.sirius.base.BaseRemoteRepository

class MainRemoteRepository(
    private val mainRetrofit: MainRetrofit,
    apiErrorHandler: ApiErrorHandler
) : BaseRemoteRepository(apiErrorHandler) {

}