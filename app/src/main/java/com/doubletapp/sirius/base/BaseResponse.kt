package com.doubletapp.sirius.base

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("status")
    open var status: String? = null

    @SerializedName("message")
    open var message: String? = null

    fun hasError(): Boolean = STATUS_ERROR.equals(status, true)

    companion object {

        private const val STATUS_ERROR = "error"
    }
}