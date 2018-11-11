package com.doubletapp.sirius.base

import android.content.SharedPreferences
import android.text.TextUtils
import com.vk.sdk.VKSdk

class AuthorizationKeyValueStorage(private val sharedPreferences: SharedPreferences) {

    private fun putString(key: String, value: String) =
            sharedPreferences
                    .edit()
                    .putString(key, value)
                    .apply()

    private fun getString(key: String): String? = sharedPreferences.getString(key, null)

    private fun putBoolean(key: String, value: Boolean) =
            sharedPreferences
                    .edit()
                    .putBoolean(key, value)
                    .apply()

    private fun getBoolean(key: String, defValue: Boolean): Boolean =
            sharedPreferences.getBoolean(key, defValue)

    fun clear() = sharedPreferences.edit().clear().apply()

    fun isAuthorized(): Boolean = getString(VK_TOKEN) != null

    fun authorize(vkToken: String) {
        putString(VK_TOKEN, vkToken)
    }

    fun login(authToken: String) {
        putString(AUTH_TOKEN, authToken)
    }

    fun isLogin(): Boolean =
        getString(AUTH_TOKEN) != null && getString(AUTH_TOKEN) != ""
    fun getToken(): String = getString(AUTH_TOKEN) ?: ""
    fun logout() {
        sharedPreferences.edit().clear().apply()
    }

    fun setTestPassed() {
        putBoolean(TEST_PASSED, true)
    }

    fun isTestPassed() : Boolean {
        return getBoolean(TEST_PASSED, false)
    }

    companion object {

        const val AUTHORIZATION_DATA = "authorization_data"
        const val VK_TOKEN = "vk_token"
        const val TEST_PASSED = "test_passed"
        const val AUTH_TOKEN = "AUTH_TOKEN"
    }
}