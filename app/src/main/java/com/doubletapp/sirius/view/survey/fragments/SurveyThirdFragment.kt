package com.doubletapp.sirius.view.survey.fragments

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doubletapp.sirius.R
import com.doubletapp.sirius.view.survey.AreasAdapter
import com.doubletapp.sirius.view.survey.SurveyActivity
import kotlinx.android.synthetic.main.fragment_survey_third.*

enum class SelectionType(val value: Int) {
    SUBJECT(0),
    GOAL(1)
}

class SurveyThirdFragment : SurveyBaseFragment() {

    companion object {
        private val ARGS_TYPE: String = "type"

        fun newInstance(type: SelectionType): SurveyThirdFragment {
            val fragment = SurveyThirdFragment()
            val bundle = Bundle()
            bundle.putInt(ARGS_TYPE, type.value)
            fragment.arguments = bundle
            return fragment
        }
    }

    var type: SelectionType = SelectionType.SUBJECT


    override fun onNextPressed() : Boolean {
        if (type == SelectionType.SUBJECT) {

        } else {

        }
        return true
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_survey_third, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = (activity as SurveyActivity).model
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(ARGS_TYPE)?.let {
            type = SelectionType.values()[it]
        }
        surveyThirdDirectionTitle.text = if (type == SelectionType.SUBJECT) {
            getString(R.string.subject_title, model.survey.direction.title)
        } else {
            getString(R.string.goal_title)
        }

        val adapter = AreasAdapter()
        adapter.singleSelection = false
        surveyThirdRecycler.layoutManager = GridLayoutManager(context, 2)
        surveyThirdRecycler.adapter = adapter

        if (type == SelectionType.SUBJECT) {
            adapter.items = mutableListOf(Pair("Физика", R.drawable.ic_physic),
                    Pair("Химия", R.drawable.ic_chemistry),
                    Pair("Математика", R.drawable.ic_math),
                    Pair("Биология", R.drawable.ic_biology),
                    Pair("Космос", R.drawable.ic_space))
        } else {
            adapter.items = mutableListOf(Pair("Элемент", R.drawable.ic_physic),
                    Pair("Элемент", R.drawable.ic_physic),
                    Pair("Элемент", R.drawable.ic_physic),
                    Pair("Элемент", R.drawable.ic_physic),
                    Pair("Стать Илоном Маском", R.drawable.ic_space))
        }
    }
}
