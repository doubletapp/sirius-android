package com.doubletapp.sirius.presentation.survey

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.doubletapp.sirius.base.AuthorizationKeyValueStorage
import com.doubletapp.sirius.base.BaseViewModel
import com.doubletapp.sirius.models.SurveyModel
import com.vk.sdk.VKSdk
import com.vk.sdk.api.*
import com.vk.sdk.api.model.VKApiUser
import com.vk.sdk.api.model.VKApiUserFull
import com.vk.sdk.api.model.VKList
import javax.inject.Inject

class SurveyViewModel
@Inject
constructor(private val authorizationKeyValueStorage: AuthorizationKeyValueStorage) : BaseViewModel() {

    private val userInfoData = MutableLiveData<VKApiUserFull>()
    val userInfo: LiveData<VKApiUserFull> get() = userInfoData
    var survey = SurveyModel()

    private val errorData = MutableLiveData<String>()
    val error: LiveData<String> get() = errorData

    fun getInfo() {
        if (VKSdk.isLoggedIn()) {
            VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "city, bdate")).executeWithListener(object : VKRequest.VKRequestListener() {
                override fun onComplete(response: VKResponse?) {
                    val user = (response?.parsedModel as VKList<VKApiUserFull>)[0]
                    userInfoData.postValue(user)
                }

                override fun onError(error: VKError?) {
                    errorData.postValue(error?.errorMessage ?: "Неизвестная ошибка")
                }
            })
        }
    }
}
