package com.doubletapp.sirius.view.survey.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.doubletapp.sirius.R
import kotlinx.android.synthetic.main.fragment_survey_format.*

class SurveyFormatFragment : SurveyBaseFragment() {
    override fun onNextPressed(): Boolean {
        return true
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_survey_format, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //todo
        val adapter = ArrayAdapter(context, R.layout.survey_spinner_item, mutableListOf("Слабоинтенсивная (1 раз в неделю)",
                "Среднеинтенсивная (2-3 раза в неделю)", "Сильноинтенсивная (4+ раза в неделю)"))
        adapter.setDropDownViewResource(R.layout.survey_spinner_dropdown_item)
        formatIntencity.adapter = adapter

        //todo
        val adapter1 = ArrayAdapter(context, R.layout.survey_spinner_item, mutableListOf("Заочная",
                "Очная"))
        adapter1.setDropDownViewResource(R.layout.survey_spinner_dropdown_item)
        formatForm.adapter = adapter1
    }

}
