package com.doubletapp.sirius.view.survey.fragments

import android.app.DatePickerDialog
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.doubletapp.sirius.R
import com.doubletapp.sirius.view.survey.SurveyActivity
import com.vk.sdk.api.model.VKApiUserFull
import kotlinx.android.synthetic.main.fragment_survey_first.*
import java.util.*

class SurveyFirstFragment() : SurveyBaseFragment() {

    private var userInfoObserver = Observer<VKApiUserFull> {
        it?.let { user ->
            surveyFirstDateValue.text = if (model.survey.bdate.isEmpty()) {
                user.bdate
            } else {
                model.survey.bdate
            }
            surveyFirstName.setText(if (model.survey.name.isEmpty()) {
                String.format("%s %s", user.first_name, user.last_name)
            } else {
                model.survey.name
            })

            val regionAdapter = ArrayAdapter<String>(context, R.layout.survey_spinner_dropdown_item, mutableListOf("Свердловская область",
                    "Курганская область", "Ленинградская область"))
            surveyFirstRegion.threshold = 1
            surveyFirstRegion.setAdapter(regionAdapter)
            surveyFirstRegion.setText(if (model.survey.city.isEmpty()) {
                user.city.title
            } else {
                model.survey.city
            })

            //todo
            val adapter = ArrayAdapter(context, R.layout.survey_spinner_item, mutableListOf("Школьник", "Студент", "Дмитрий"))
            adapter.setDropDownViewResource(R.layout.survey_spinner_dropdown_item)
            surveyFirstRole.adapter = adapter

            surveyFirstDateBtn.setOnClickListener({
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)


                val dpd = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in textbox
                    surveyFirstDateValue.text = String.format("%d.%d.%d", dayOfMonth, monthOfYear, year)
                }, year, month, day)
                dpd.show()
            })
        }
    }

    override fun onNextPressed(): Boolean {
        model.survey.name = surveyFirstName.text.toString()
        model.survey.bdate = surveyFirstDateValue.text.toString()
        model.survey.city = surveyFirstRegion.text.toString()
        model.survey.role = surveyFirstRole.selectedItem.toString()
        return true
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_survey_first, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = (activity as SurveyActivity).model
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model.userInfo.observe(this, userInfoObserver)
        model.getInfo()
    }

}
