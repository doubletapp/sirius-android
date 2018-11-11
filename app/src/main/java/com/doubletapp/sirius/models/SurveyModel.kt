package com.doubletapp.sirius.models

import com.doubletapp.sirius.view.survey.Sphere

class SurveyModel(var name: String = "",
                  var bdate: String = "",
                  var city: String = "",
                  var role: String = "",
                  var educationInstitution: String = "",
                  var classNumber: Int = 1,
                  var direction: Sphere = Sphere.UNDEFINED,
                  var subject: String = "",
                  var goal: Pair<String, String> = Pair("","")
                  ) {

}