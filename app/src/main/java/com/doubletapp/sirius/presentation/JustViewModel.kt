package com.doubletapp.sirius.presentation

import com.doubletapp.sirius.base.BaseViewModel
import com.doubletapp.sirius.domain.just.JustInteractor
import javax.inject.Inject

class JustViewModel
@Inject
constructor(
    private val justInteractor: JustInteractor
) : BaseViewModel() {

}