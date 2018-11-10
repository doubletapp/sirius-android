package com.doubletapp.sirius.presentation

import com.doubletapp.sirius.base.BaseViewModel
import com.doubletapp.sirius.domain.login.LoginInteractor
import javax.inject.Inject

class SettingsViewModel
@Inject
constructor(private val loginInteractor: LoginInteractor) : BaseViewModel() {

    fun logout() {
        loginInteractor.logout()
    }
}