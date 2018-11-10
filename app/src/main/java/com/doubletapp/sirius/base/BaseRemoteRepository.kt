package com.doubletapp.sirius.base

import io.reactivex.SingleTransformer
import io.reactivex.schedulers.Schedulers

abstract class BaseRemoteRepository(private val mApiErrorHandler: ApiErrorHandler) {

    protected fun <T : BaseResponse> apiCompose(): SingleTransformer<T, T> {
        return SingleTransformer { single ->
            single.onErrorResumeNext(mApiErrorHandler.handleError())
                    .subscribeOn(Schedulers.io())
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