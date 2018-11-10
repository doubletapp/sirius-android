package com.doubletapp.sirius.view.survey.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.doubletapp.sirius.R
import com.doubletapp.sirius.view.survey.AreasAdapter
import com.doubletapp.sirius.view.survey.Sphere
import com.doubletapp.sirius.view.survey.SurveyActivity
import kotlinx.android.synthetic.main.fragment_survey_second.*

class SurveySecondFragment : SurveyBaseFragment() {

    override fun onNextPressed() : Boolean {
        val idx = (surveySecondRecycler.adapter as AreasAdapter).getCurrentSphere()
        if (idx <0 || Sphere.values()[idx] == Sphere.UNDEFINED) {
            AlertDialog.Builder(context)
                    .setMessage("Пожалуйста, выберите область")
                    .setCancelable(true)
                    .setPositiveButton(android.R.string.ok, null)
                    .show()
            return false
        }
        model.survey.educationInstitution = surveySecondEducation.selectedItem.toString()
        model.survey.direction = Sphere.values()[(surveySecondRecycler.adapter as AreasAdapter).getCurrentSphere()]
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = (activity as SurveyActivity).model
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_survey_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //todo
        val mock = mutableListOf("Гимназия 13, СПб", "УрФУ, Екб", "МГУ, Москва")
        val spinnerAdapter = ArrayAdapter(context, R.layout.survey_spinner_dropdown_item, mock)
        spinnerAdapter.setDropDownViewResource(R.layout.survey_spinner_dropdown_item)
        surveySecondEducation.adapter = spinnerAdapter
        if (!model.survey.educationInstitution.isEmpty())
            surveySecondEducation.setSelection(mock.indexOfFirst {
                it == model.survey.educationInstitution
            })

        val adapter = AreasAdapter()

        surveySecondRecycler.layoutManager = GridLayoutManager(context, 2)
        surveySecondRecycler.adapter = adapter

        adapter.items = mutableListOf(Pair(Sphere.SCIENCE.title, R.drawable.selectable_book_drawable),
                Pair(Sphere.ART.title, R.drawable.selectable_music_drawable),
                Pair(Sphere.LITERATURE.title, R.drawable.selectable_pen_drawable),
                Pair(Sphere.SPORT.title, R.drawable.selectable_sport_drawable)
        )

        if (!model.survey.direction.title.isEmpty()) {
            adapter.checkedIdx = adapter.items.indexOfFirst {
                it.first == model.survey.direction.title
            }
            adapter.notifyDataSetChanged()
        }

    }

}