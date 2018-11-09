package com.doubletapp.sirius.view.survey

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.doubletapp.sirius.R
import com.doubletapp.sirius.base.BaseActivity
import com.doubletapp.sirius.base.BaseFragment
import com.doubletapp.sirius.extensions.showFragment
import com.doubletapp.sirius.view.survey.fragments.SurveyFirstFragment
import kotlinx.android.synthetic.main.activity_survey.*

class SurveyActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, SurveyActivity::class.java)
            context.startActivity(
                    intent.setFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK or
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                                    Intent.FLAG_ACTIVITY_CLEAR_TOP
                    )
            )
        }
    }

    var currentFragment: Int = 0
    var fragments: MutableList<BaseFragment> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        fragments.add(SurveyFirstFragment())
    }

    override fun onResume() {
        super.onResume()
        toggleFragment()
    }

    private fun toggleFragment() {
        surveyQuestionTitle.text = getString(R.string.survey_question_title, currentFragment + 1)
        showFragment(R.id.surveyFragmentContainer,
                fragments[currentFragment],
                currentFragment.toString(),
                true)
    }

    override fun onBackPressed() {
        if (currentFragment == 1)
            finish()
        super.onBackPressed()
    }

}
