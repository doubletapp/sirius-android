package com.doubletapp.sirius.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.doubletapp.sirius.base.BaseViewModel
import com.doubletapp.sirius.domain.main.MainInteractor
import com.vk.sdk.VKSdk
import com.vk.sdk.api.*
import com.vk.sdk.api.model.VKApiUserFull
import com.vk.sdk.api.model.VKList
import javax.inject.Inject

class MainViewModel
@Inject
constructor(
    private val mainInteractor: MainInteractor
) : BaseViewModel() {

    private val userInfoData = MutableLiveData<VKApiUserFull>()
    val userInfo: LiveData<VKApiUserFull> get() = userInfoData

    fun getUser() {
        if (VKSdk.isLoggedIn()) {
            VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "city,bdate,photo_200")).executeWithListener(object : VKRequest.VKRequestListener() {
                override fun onComplete(response: VKResponse?) {
                    val user = (response?.parsedModel as VKList<VKApiUserFull>)[0]
                    userInfoData.postValue(user)
                }

                override fun onError(error: VKError?) {  }
            })
        }
    }

    fun getCourses() {
        disposables.add(
            mainInteractor.getCourses()
                .subscribe()
        )
    }
}