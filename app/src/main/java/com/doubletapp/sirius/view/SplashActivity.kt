package com.doubletapp.sirius.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import com.doubletapp.sirius.R
import com.doubletapp.sirius.extensions.toast
import com.doubletapp.sirius.view.survey.SurveyActivity
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKApi
import com.vk.sdk.api.VKError
import com.vk.sdk.util.VKUtil
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var splashActivity: SplashActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_splash)
        if (!VKSdk.isLoggedIn()) {
            Handler().postDelayed({ showAuth() }, 1000)
        } else {
            if (true) {
                showNextStep()
            } else {
                Handler().postDelayed({ MainActivity.start(this) }, 1000)
            }
        }
        splash_auth_vk_button.setOnClickListener {
            Log.d("!!!", VKUtil.getCertificateFingerprint(this, this.packageName)[0])
            VKSdk.login(this, "friends", "offline", "groups") }
    }

    private fun showAuth() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this, R.layout.activity_splash_2)
        TransitionManager.beginDelayedTransition(splash_first_layout)
        constraintSet.applyTo(splash_first_layout)
    }

    fun showNextStep() {
        //todo DIMA!
        if (false) {
            MainActivity.start(this@SplashActivity)
        } else {
            SurveyActivity.start(this@SplashActivity)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
                override fun onResult(res: VKAccessToken) {
                    toast(res.accessToken + res.email)
                    showNextStep()
                }
                override fun onError(error: VKError) {}
            })
        ) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
