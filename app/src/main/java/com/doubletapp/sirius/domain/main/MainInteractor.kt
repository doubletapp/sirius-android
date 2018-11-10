package com.doubletapp.sirius.domain.main

import com.doubletapp.sirius.base.BaseResponse
import io.reactivex.Single

class MainInteractor(
    private val mainRepository: MainRemoteRepository
) {
    fun getCourses(): Single<BaseResponse> = mainRepository.getCourses()
}