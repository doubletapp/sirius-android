package com.doubletapp.sirius.domain.login

import com.doubletapp.sirius.base.BaseResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginRetrofit {
    @POST("auth/")
    @FormUrlEncoded
    fun auth(@Field("vk_id") vkId: String,
             @Field("vk_token") vkToken: String,
             @Field("sirius_id") siriusId: String,
             @Field("sirius_password") siriusPassword: String,
             @Field("city") city: String): Single<LoginResponse>
}