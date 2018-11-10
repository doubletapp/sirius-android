package com.doubletapp.sirius.domain.main

import com.doubletapp.sirius.base.BaseResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MainRetrofit {
    @GET("course/")
    fun  getCourses(): Single<BaseResponse>
}