package com.doubletapp.sirius.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.doubletapp.sirius.R
import com.doubletapp.sirius.view.survey.SurveyActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed( { SurveyActivity.start(this) }, 1000)
    }
}
