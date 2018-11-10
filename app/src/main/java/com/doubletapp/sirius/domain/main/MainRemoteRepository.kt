package com.doubletapp.sirius.domain.main

import com.doubletapp.sirius.base.ApiErrorHandler
import com.doubletapp.sirius.base.BaseRemoteRepository
import com.doubletapp.sirius.base.BaseResponse
import io.reactivex.Single

class MainRemoteRepository(
    private val mainRetrofit: MainRetrofit,
    apiErrorHandler: ApiErrorHandler
) : BaseRemoteRepository(apiErrorHandler) {
    fun getCourses(): Single<BaseResponse> =
        mainRetrofit.getCourses().compose(apiCompose())
}