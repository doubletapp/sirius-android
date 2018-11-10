package com.doubletapp.sirius.view.survey.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.doubletapp.sirius.R
import com.doubletapp.sirius.view.survey.SurveyActivity
import kotlinx.android.synthetic.main.fragment_survey_achivements.*
import java.util.*

class AchievementsSurveyFragment : SurveyBaseFragment() {

    override fun onNextPressed(): Boolean {
        return true
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_survey_achivements, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = (activity as SurveyActivity).model
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        achAddCard.setOnClickListener {
            achAddContainer.visibility = VISIBLE
            (activity as SurveyActivity).hideBottom(true)
        }

        achAdd.setOnClickListener {
            achAddContainer.visibility = GONE
            (activity as SurveyActivity).hideBottom(false)
            achAddTitle.text = achTitle.text
        }

        //todo
        val adapter = ArrayAdapter(context, R.layout.survey_spinner_dropdown_item, mutableListOf("Школьник", "Студент", "Дмитрий"))
        adapter.setDropDownViewResource(R.layout.survey_spinner_dropdown_item)
        achType.adapter = adapter

        achDateBtn.setOnClickListener({
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                // Display Selected date in textbox
                achDateValue.text = String.format("%d.%d.%d", dayOfMonth, monthOfYear, year)
            }, year, month, day)
            dpd.show()
        })
    }
}
