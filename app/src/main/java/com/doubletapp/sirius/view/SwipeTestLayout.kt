package com.doubletapp.sirius.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.doubletapp.sirius.R
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.android.synthetic.main.fragment_swipe_test_layout.*

class SwipeTestLayout : Fragment(), CardStackListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_swipe_test_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = CardStackLayoutManager(context, this)

        cardStackView.layoutManager = layoutManager
        cardStackView.adapter = SwipeTestAdapter()
        swipeTestToolbar.setNavigationOnClickListener { activity?.onBackPressed() }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
    }

    override fun onCardCanceled() {
    }

    override fun onCardRewound() {
    }
}
