package com.doubletapp.sirius.view

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.view.View
import com.doubletapp.sirius.R
import com.doubletapp.sirius.base.BaseActivity
import com.doubletapp.sirius.extensions.toast
import com.doubletapp.sirius.view.survey.SurveyActivity
import com.doubletapp.sirius.presentation.SplashViewModel
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, SplashActivity::class.java)
            context.startActivity(
                    intent.setFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK or
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                                    Intent.FLAG_ACTIVITY_CLEAR_TOP
                    )
            )
        }
    }

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_splash)
        if (!splashViewModel.isLoggedIn()) {
            Handler().postDelayed({ showAuth() }, 1000)
        } else {
            showNextStep()

        }
        splash_auth_vk_button.setOnClickListener { VKSdk.login(this, "friends", "offline", "groups") }
    }

    private fun showAuth() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this, R.layout.activity_splash_2)
        TransitionManager.beginDelayedTransition(splash_first_layout)
        constraintSet.applyTo(splash_first_layout)
    }

    fun showNextStep() {
        if (splashViewModel.isShowTest()) {
            MainActivity.start(this@SplashActivity)
        } else {
            SurveyActivity.start(this@SplashActivity)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
                override fun onResult(res: VKAccessToken) {
                    splashViewModel.login.observe(this@SplashActivity, Observer {
                        showNextStep()
                    })
                    splashViewModel.login(res.userId, res.accessToken, "1", "1", "", this@SplashActivity)
                }
                override fun onError(error: VKError) {}
            })
        ) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
