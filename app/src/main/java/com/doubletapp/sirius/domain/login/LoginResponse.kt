package com.doubletapp.sirius.domain.login

import com.doubletapp.sirius.base.BaseResponse

data class LoginResponse(
        val vk_id: Int,
        val vk_token: String,
        val sirius_id: Int,
        val sirius_password: String,
        val auth_token: String,
        val city: String
) : BaseResponse()