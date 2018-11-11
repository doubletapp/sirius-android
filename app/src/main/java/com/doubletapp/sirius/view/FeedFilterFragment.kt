package com.doubletapp.sirius.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doubletapp.sirius.R
import com.doubletapp.sirius.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_feed_filter.*
import android.content.res.ColorStateList
import android.content.res.XmlResourceParser
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.feed_header_layout.*


class FeedFilterFragment : BaseFragment() {

    companion object {
        const val TAG: String = "FeedFilterFragment"
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter(context, R.layout.survey_spinner_item, mutableListOf("Свердловская область",
                "Ленинградская область", "Московская область", "Курганская область"))
        adapter.setDropDownViewResource(R.layout.survey_spinner_dropdown_item)
        feedFilterRegion.adapter = adapter

        feedFilterLection.setOnClickListener {
            feedFilterLection.isSelected = !feedFilterLection.isSelected
        }
        feedFilterConf.setOnClickListener {
            feedFilterConf.isSelected = !feedFilterConf.isSelected
        }
        feedFilterOlymp.setOnClickListener {
            feedFilterOlymp.isSelected = !feedFilterOlymp.isSelected
        }
    }
}
