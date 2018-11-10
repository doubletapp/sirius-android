package com.doubletapp.sirius.view.survey.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.doubletapp.sirius.R
import com.doubletapp.sirius.models.EventModel
import com.doubletapp.sirius.view.survey.SurveyActivity
import kotlinx.android.synthetic.main.fragment_survey_event.*

class SurveyEventFragment : SurveyBaseFragment() {

    var eventModel = EventModel()

    override fun onNextPressed(): Boolean {
        if (eventNew.visibility == VISIBLE) {
            eventModel.title = eventName.text.toString()
            eventTitle.text = eventModel.title
            setSelected(false)
            return false
        }
        return true
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_survey_event, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = (activity as SurveyActivity).model
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventAddContainer.setOnClickListener({
            setSelected(!eventAddContainer.isSelected)
        })
        eventCheckerPositive.setOnClickListener({
            eventModel.isSirius = true
        })
        eventCheckerNegative.setOnClickListener({
            eventModel.isSirius = false
        })
        val regionAdapter = ArrayAdapter<String>(context, R.layout.survey_spinner_dropdown_item, mutableListOf())
        eventName.threshold = 1
        eventName.setAdapter(regionAdapter)
    }

    private fun setSelected(isSelected: Boolean) {
        eventNew.visibility = if (isSelected) VISIBLE else GONE
        eventAddContainer.isSelected = isSelected
        eventIndicator.visibility = if (isSelected) VISIBLE else GONE
        eventTitle.setTextColor(eventTitle.context.getColor(if (isSelected) R.color.white else R.color.text_dark_gray))
    }

}