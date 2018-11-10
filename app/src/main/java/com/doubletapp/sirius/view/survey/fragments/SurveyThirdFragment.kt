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
import com.doubletapp.sirius.view.survey.SurveyActivity
import kotlinx.android.synthetic.main.fragment_survey_third.*
import kotlinx.android.synthetic.main.layout_dialog_select.view.*

enum class SelectionType(val value: Int) {
    SUBJECT(0),
    GOAL(1)
}

class SurveyThirdFragment : SurveyBaseFragment() {

    private val adapter = AreasAdapter()
    var isSecondGoalNeed = false
    var goal = Pair("", "")

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


    override fun onNextPressed(): Boolean {
        if (type == SelectionType.SUBJECT) {
            if (adapter.checkedIdx != 2) {
                showError(getString(R.string.survey_not_math_error))
                return false
            }
            model.survey.subject = adapter.items[adapter.checkedIdx].first
        } else {
            if (goal.first.isEmpty()) {
                showError(getString(R.string.survey_empty_first_goal_error))
                return false
            }
            if (isSecondGoalNeed && goal.second.isEmpty()) {
                //todo another error
                showError(getString(R.string.survey_empty_first_goal_error))
                return false
            }
            model.survey.goal = goal
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
        adapter.singleSelection = false
        surveyThirdRecycler.layoutManager = GridLayoutManager(context, 2)
        surveyThirdRecycler.adapter = adapter
        adapter.singleSelection = true

        if (type == SelectionType.SUBJECT) {
            adapter.items = mutableListOf(Pair(getString(R.string.physics), R.drawable.ic_physic_selector),
                    Pair(getString(R.string.chemistry), R.drawable.ic_chemistry_selector),
                    Pair(getString(R.string.math), R.drawable.ic_math_selector),
                    Pair(getString(R.string.biology), R.drawable.ic_biology_selector),
                    Pair(getString(R.string.space), R.drawable.ic_space_selector))
        } else {
            adapter.items = mutableListOf(Pair(getString(R.string.goal_one), R.drawable.ic_physic),
                    Pair(getString(R.string.goal_two), R.drawable.ic_physic),
                    Pair(getString(R.string.goal_three), R.drawable.ic_physic),
                    Pair(getString(R.string.goal_four), R.drawable.ic_physic),
                    Pair(getString(R.string.goal_mask), R.drawable.ic_space))
            adapter.listener = object : AreasAdapter.OnSelectionListener {
                override fun itemSelected(position: Int) {
                    goal = Pair(adapter.items[position].first, "")
                    when (position) {
                        0, 4 -> {
                            isSecondGoalNeed = false
                        }
                        1 -> {
                            isSecondGoalNeed = true
                            showCreateCategoryDialog(getString(R.string.select_dialog_olymp_title), mutableListOf("Всероссийская олимпиада школьников по математике",
                                    "Всероссийская олимпиада школьников по физике"))
                        }
                        2 -> {
                            isSecondGoalNeed = true
                            showCreateCategoryDialog(getString(R.string.select_dialog_university_title), mutableListOf("УрФУ", "УрГУ", "МГУ"))
                        }
                        3 -> {
                            isSecondGoalNeed = true
                            showCreateCategoryDialog(getString(R.string.select_dialog_job_title), mutableListOf("Doubletapp", "Doubletapp", "Doubletapp"))

                        }
                    }
                }
            }
        }
    }

    private fun showError(message: String) {
        AlertDialog.Builder(context)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok, null)
                .show()
    }

    private fun showCreateCategoryDialog(title: String, list: MutableList<String>) {
        val context = this
        val builder = AlertDialog.Builder(this.context)


        val view = layoutInflater.inflate(R.layout.layout_dialog_select, null)
        builder.setView(view)

        val dialogAdapter = ArrayAdapter<String>(this.context, R.layout.survey_spinner_dropdown_item, list)

        view.dialogSelectInputField.threshold = 1
        view.dialogSelectInputField.setAdapter(dialogAdapter)
        view.dialogSelectTitleField.text = title

        // set up the ok button
        builder.setPositiveButton(android.R.string.ok) { dialog, p1 ->
            goal = Pair(goal.first, view.dialogSelectInputField.text.toString())
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, p1 ->
            dialog.dismiss()
        }

        builder.show();
    }
}
