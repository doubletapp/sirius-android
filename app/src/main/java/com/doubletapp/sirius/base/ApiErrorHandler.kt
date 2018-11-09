package com.doubletapp.sirius.base

import android.content.Context
import com.doubletapp.sirius.R
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Function
import retrofit2.HttpException
import java.io.IOException

class ApiErrorHandler(
        private val context: Context,
        private val authorizationKeyValueStorage: AuthorizationKeyValueStorage
) {

    internal fun <T : BaseResponse> handleError(): Function<Throwable, SingleSource<T>> =
            Function { throwable ->
                if (throwable is IOException) {
                    return@Function Single.error<T>(getInternetError(throwable))
                }
                if (throwable is HttpException) {
                    if (throwable.response().code() == 403) {
                        signOut()
                    } else {
                        return@Function Single.error<T>(getHttpError(throwable))
                    }
                }
                return@Function Single.error<T>(throwable)
            }

    private fun getInternetError(cause: Throwable): Exception {
        return Exception(context.getString(R.string.internet_error_message), cause)
    }

    private fun getHttpError(cause: Throwable): Exception {
        return Exception(context.getString(R.string.http_error_message), cause)
    }

    private fun signOut() {

    }
}