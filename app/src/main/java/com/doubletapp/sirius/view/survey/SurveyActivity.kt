package com.doubletapp.sirius.view.survey

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.doubletapp.sirius.R
import com.doubletapp.sirius.base.BaseActivity
import com.doubletapp.sirius.extensions.showFragment
import com.doubletapp.sirius.presentation.survey.SurveyViewModel
import com.doubletapp.sirius.view.survey.fragments.*
import kotlinx.android.synthetic.main.activity_survey.*
import javax.inject.Inject

class SurveyActivity : BaseActivity() {

    @Inject
    lateinit var model: SurveyViewModel

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
    var fragments: MutableList<SurveyBaseFragment> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        fragments.add(SurveyFirstFragment())
        fragments.add(SurveySecondFragment())
        fragments.add(SurveyThirdFragment.newInstance(SelectionType.SUBJECT))
        fragments.add(SurveyThirdFragment.newInstance(SelectionType.GOAL))
        setSupportActionBar(surveyToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.title = ""
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        toggleFragment()
        surveyNext.setOnClickListener {
            if (fragments[currentFragment].onNextPressed()) {
                if (fragments.count() > currentFragment) {
                    currentFragment++
                    toggleFragment()
                }
            }
        }
    }

    private fun changeText() {
        surveyQuestionTitle.text = getString(R.string.survey_question_title, currentFragment + 1)

    }

    private fun toggleFragment() {
        changeText()
        showFragment(R.id.surveyFragmentContainer,
                fragments[currentFragment],
                currentFragment.toString(),
                true)
    }

    override fun onBackPressed() {
        if (currentFragment == 0)
            finish()
        currentFragment--
        changeText()
        super.onBackPressed()
    }

}
