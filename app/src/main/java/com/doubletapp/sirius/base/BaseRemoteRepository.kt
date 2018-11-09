package com.doubletapp.sirius.base

import io.reactivex.SingleTransformer

abstract class BaseRemoteRepository(private val mApiErrorHandler: ApiErrorHandler) {

    protected fun <T : BaseResponse> apiCompose(): SingleTransformer<T, T> {
        return SingleTransformer { single ->
            single.onErrorResumeNext(mApiErrorHandler.handleError())
                    .doOnSuccess(::throwIfError)
        }
    }

    @Throws(Exception::class)
    private fun throwIfError(baseResponse: BaseResponse) {
        if (baseResponse.hasError()) {
            throw RuntimeException(baseResponse.message)
        }
    }
}