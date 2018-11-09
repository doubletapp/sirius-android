package com.doubletapp.sirius.presentation

import com.doubletapp.sirius.base.BaseViewModel
import com.doubletapp.sirius.domain.main.MainInteractor
import javax.inject.Inject

class MainViewModel
@Inject
constructor(
    private val mainInteractor: MainInteractor
) : BaseViewModel() {

}